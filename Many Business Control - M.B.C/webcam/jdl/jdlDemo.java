/**
jdl api demo
copyright 2009
N.Peters
humatic GmbH
Berlin, Germany
**/

import de.humatic.dsj.*;

import de.humatic.jdl.*;

import javax.swing.*;

/**
Simple demo application to demonstrate use of jdl for accessing proprietary interfaces on Blackmagic Design filters in
a dsj filtergraph.
**/

public class jdlDemo implements java.beans.PropertyChangeListener {

	DSFiltergraph graph;

	private DSFilter decklinkFilter,
					 decklinkAudioFilter;

	private DecklinkIOControl dio;

	private DecklinkKeyer dk;

	private DecklinkRawDeviceControl drdc;

	private DecklinkStatus ds;

	private DecklinkCaptureBanner db;

	private DSMediaType vmt;

	private byte[] yuvData;

	/** change to switch between capture from and output to Decklink card **/

	private boolean output = false;

	public jdlDemo(){}

	public void buildGraph() {

		if (output) {

			/**
			Playing out to a Decklink card. Note the use of the HEADLESS flag, which tells dsj to not mess around with the graph
			after we are done building it. Without this the Decklink renderer filter would be replaced by a dsj Video Renderer.
			**/

			graph = DSGraph.createFilterGraph(DSFiltergraph.HEADLESS, this);

			DSFilterInfo decklink = DSFilterInfo.filterInfoForName("Decklink Video Render");

			decklinkFilter = graph.addFilterToGraph(decklink);

			DSFilterInfo decklinkAudio = DSFilterInfo.filterInfoForName("Decklink Audio Render");

			decklinkAudioFilter = graph.addFilterToGraph(decklinkAudio);

			DSFilter sourceFilter = null;

			if (true) {

				/** load a file with alpha channel, the one used here can be found in the Blackmagic SDK **/

				sourceFilter = ((DSGraph)graph).addFileSourceFilter("C:/Program Files (x86)/Blackmagic DeckLink SDK 7.3/Win/DirectShow/Samples/DecklinkKey/res/alpha test PAL 4x3 8-bit.avi");

			} else {

				sourceFilter = ((DSGraph)graph).insertJavaSourceFilter(720, 576, 25, 0);

			}

			DSFilter.DSPin srcOut = sourceFilter.getPin(DSFilterInfo.DSPinInfo.PINDIR_OUTPUT, DSMediaType.MT_AUDIO, false, 0);

			if (srcOut != null) sourceFilter.renderEx(srcOut);

			DSFilter.DSPin srcOut2 = sourceFilter.getPin(DSFilterInfo.DSPinInfo.PINDIR_OUTPUT, DSMediaType.MT_VIDEO, false , 0);

			if (srcOut2 != null) sourceFilter.renderEx(srcOut2);

			if (srcOut == null && srcOut2 == null) sourceFilter.renderEx(sourceFilter.getPin(0,0));

			((DSGraph)graph).setupComplete();

			graph.setLoop(true);

			graph.setRate(0);

		} else {

			/**
			Capture from a Decklink card's inputs. Note that it is important that the selected input format and the
			video input processing settings in the cards control panel match the incoming video signal or you might
			not get anything at all.
			**/

			if (true) {

				/** Use a dsj widget to select devices. Makes format selection a bit easier **/

				graph = DSCapture.fromUserDialog(new java.awt.Frame(), DSFiltergraph.DD7, this);

			} else {

				/** Pick the Decklink filters by hand **/

				DSFilterInfo[][] dsi = DSCapture.queryDevices(1);

				int vIdx = -1;

				int aIdx = -1;

				for (DSFilterInfo fi : dsi[0]) {

					vIdx++;

					if (dsi[0][vIdx].getName().indexOf("Decklink") != -1) {

						dsi[0][vIdx].getDownstreamPins()[0].setPreferredFormat(2);

						break;

					}

				}

				for (DSFilterInfo fi : dsi[1]) {

					aIdx++;

					if (dsi[1][aIdx].getName().indexOf("Decklink") != -1) break;

				}

				graph = new DSCapture(DSFiltergraph.DD7, dsi[0][vIdx], false, dsi[1][aIdx], this);

			}

		}

		Decklink d = new Decklink(graph);

		dio = (DecklinkIOControl)(d.getInterface(Decklink.IO_CONTROL));

		int features = dio.getIOFeatures();

		System.out.println("IOFeatures: "+Long.toHexString((long)(features)));

		for (int i = 0; i < DecklinkIOControl.featureStrings.length; i++) {

			if ((features & (1 << i)) != 0) {

				System.out.println(DecklinkIOControl.featureStrings[i]);

			}

		}

		JPanel ctrl = new JPanel();

		/** There is no method in the Decklink api to get the currently selected input...**/

		final JComboBox inputs = new JComboBox(new String[]{"SDI", "Component", "Composite", "S-Video", "HDMI"});

		inputs.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e){

				try{ dio.setVideoInput(inputs.getSelectedIndex(), 0); } catch (Exception ex){ System.out.println(ex.toString());}
			}
		});

		ctrl.add(inputs);

		if (output){

			/** test the card's keying features. Change inputs / outputs to your needs **/

			dio.setVideoInput(2, 0);

			dio.setAnalogueOutput(2, 0);

			dk = (DecklinkKeyer)(d.getInterface(Decklink.KEYER));

			System.out.println("KeySupport: "+dk.getKeyingSupport());

			dk.setAlphaBlendMode(1, 0);

			dk.setAlphaLevel(128);

			ctrl.add(createKeyCtrls());

			try{

				/**
				The interface as it seems will also be available on cards that have no device control port (like Intensity), but
				it won't be functional - the sync method will return nonsense, the async method won't return anything.
				**/

				drdc = (DecklinkRawDeviceControl)(d.getInterface(Decklink.DEV_CTRL));

			} catch (Exception e) {

				System.err.println(e.toString());

				return;

			}

			ctrl.add(createDeviceCtrls());

		} else {

			/** test the "CaptureBanner" interface **/

			dio.setVideoInput(2, 0);

			db = (DecklinkCaptureBanner)(d.getInterface(Decklink.CAP_BANNER));

			vmt = d.getFilter(Decklink.VIDEO_CAPTURE).getPin(DSFilter.PINDIR_OUTPUT, 0).getConnectionMediaType();

			java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(vmt.getWidth(), vmt.getHeight(), java.awt.image.BufferedImage.TYPE_INT_ARGB);

			java.awt.Graphics2D g2d = img.createGraphics();

			g2d.setColor(java.awt.Color.red);

			g2d.fillRect(100, 100, 100, 100);

			g2d.setColor(java.awt.Color.green.darker());

			g2d.fillRect(vmt.getWidth()-200, vmt.getHeight()-200, 100, 100);

			g2d.setColor(java.awt.Color.blue);

			g2d.fillRect(vmt.getWidth()-200, 100, 100, 100);

			g2d.setColor(java.awt.Color.yellow);

			g2d.fillRect(100, vmt.getHeight()-200, 100, 100);

			g2d.setColor(java.awt.Color.green);

			g2d.setFont(new java.awt.Font("Arial", 1, 56));

			g2d.drawString("jdl - no input", vmt.getWidth()/2-150, vmt.getHeight()/2+25);

			yuvData = db.toYUV(img, vmt);

			ds = (DecklinkStatus)(d.getInterface(Decklink.STATUS));

			System.out.println("VideoStatus: "+ds.getVideoInputStatus()[1]);

			ds.registerVideoStatusChangeEvent();

		}

		javax.swing.JFrame f = new javax.swing.JFrame(" humatic - dsj / jdl");

		f.add(java.awt.BorderLayout.NORTH, ctrl);

		f.add(java.awt.BorderLayout.CENTER, graph.asComponent());

		f.add(java.awt.BorderLayout.SOUTH, new SwingMovieController(graph));

		f.pack();
		f.setVisible(true);

		f.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		graph.play();

	}

	private JPanel createKeyCtrls() {

		JPanel keyCtrls = new JPanel(new java.awt.BorderLayout());
		keyCtrls.setBorder(new javax.swing.border.TitledBorder("Keying"));
		final JCheckBox keyState = new JCheckBox("on/off");
		keyState.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e){
				try{ dk.setAlphaBlendMode(keyState.isSelected() ? 1 : 0, 0); } catch (Exception ex){ System.out.println(ex.toString());}
			}
		});

		final JSlider level = new JSlider(0, 255, 128);
		level.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent e) {
				try{
					dk.setAlphaLevel(level.getValue());
				}catch (Exception qe) {}
			}
		});

		JPanel ramp = new JPanel(new java.awt.GridLayout(1,2));
		final JButton up = new JButton("up");
		up.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e){
				try{ dk.alphaRamp(1, 25); } catch (Exception ex){ System.out.println(ex.toString());}
			}
		});

		final JButton down = new JButton("down");
		down.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e){
				try{ dk.alphaRamp(-1, 25); } catch (Exception ex){ System.out.println(ex.toString());}
			}
		});
		ramp.add(up);
		ramp.add(down);

		keyCtrls.add(java.awt.BorderLayout.WEST, keyState);
		keyCtrls.add(java.awt.BorderLayout.CENTER, level);
		keyCtrls.add(java.awt.BorderLayout.EAST, ramp);

		return keyCtrls;

	}

	private JPanel createDeviceCtrls() {

		JPanel devCtrls = new JPanel(new java.awt.GridLayout(1, 0));
		devCtrls.setBorder(new javax.swing.border.TitledBorder("Dev.Control"));

		final JButton play = new JButton("play");
		play.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e){
				try{

					/**
					There is no particular reason to use syncronous execution with play and async with stop.
					Just to show / test both methods.
					**/

					byte[] response = drdc.sendRawCommand(new byte[]{(byte)0x20, 0x01, 0x21});

					DSJUtils.dump("syncronous device reply: ", response);

				} catch (Exception ex){ System.out.println(ex.toString());}
			}
		});

		final JButton stop = new JButton("stop");
		stop.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e){
				try{ drdc.sendRawCommandAsync(new byte[]{(byte)0x20, 0x00, 0x20}); } catch (Exception ex){ System.out.println(ex.toString());}
			}
		});

		final JButton time = new JButton("time");
		time.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e){
				try{ drdc.sendRawCommandAsync(new byte[]{(byte)0x61, 0x0C, 0x04, 0x71}); } catch (Exception ex){ System.out.println(ex.toString());}
			}
		});

		devCtrls.add(play);
		devCtrls.add(stop);
		devCtrls.add(time);

		return devCtrls;

	}

	public void propertyChange(java.beans.PropertyChangeEvent pe) {

		switch(DSJUtils.getEventType(pe)) {

			case Decklink.INPUT_STATUS_CHANGED:

				try{

					int vips = ds.getVideoInputStatus()[0];

					System.out.println("VideoStatus: "+vips+ ", GenlockStatus: "+ds.getVideoInputStatus()[1]);

					/**
					Use the DecklinkCaptureBanner interface to present some "sorry, no signal" message when
					video status is 0.
					**/

					if (vips == 0 && db != null) db.setCaptureBanner(yuvData);

				} catch (Exception e){e.printStackTrace();}

				break;

			case Decklink.RS422_ASYNC_REPLY:

				try{

					byte[] response = (byte[])(pe.getOldValue());

					DSJUtils.dump("Async device reply: ", response);

				} catch (Exception e){e.printStackTrace();}

				break;

		}

	}

	public static void main(String[] args){

		new jdlDemo().buildGraph();

	}

}
