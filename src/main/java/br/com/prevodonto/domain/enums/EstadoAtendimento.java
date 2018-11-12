package br.com.prevodonto.domain.enums;


public enum EstadoAtendimento {
	PENDENTE(1,"Pendente"),
	ATENDIDO(2,"Atendido"),
	ATRASADO(3,"Atrasado"),
	CANCELADO(4,"Cancelado");
	
	private Integer cod;
	private String descricao;
	private EstadoAtendimento(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	public Integer getCod() {
		return cod;
	}
	public String getDescricao() {
		return descricao;
	}
	public static EstadoAtendimento toEnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		for (EstadoAtendimento estado: EstadoAtendimento.values()) {
			if (cod.equals(estado.getCod())) {
				return estado;
			}
		}
		throw new IllegalArgumentException("Codigo inválido"+cod);
	}
	
	

}
