package beans;

public class obreiro extends pessoa{
	private int id_Obreiro;
	private int batismo;
	private String dataBatismo;
	private String uncao;
	private String dataUncao;
	private String cargo;
	
	
	public int getId_Obreiro() {
		return id_Obreiro;
	}
	public void setId_Obreiro(int id_Obreiro) {
		this.id_Obreiro = id_Obreiro;
	}
	public int getBatismo() {
		return batismo;
	}
	public void setBatismo(int batismo) {
		this.batismo = batismo;
	}
	public String getDataBatismo() {
		return dataBatismo;
	}
	public void setDataBatismo(String dataBatismo) {
		this.dataBatismo = dataBatismo;
	}
	public String getUncao() {
		return uncao;
	}
	public void setUncao(String uncao) {
		this.uncao = uncao;
	}
	public String getDataUncao() {
		return dataUncao;
	}
	public void setDataUncao(String dataUncao) {
		this.dataUncao = dataUncao;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	

}
