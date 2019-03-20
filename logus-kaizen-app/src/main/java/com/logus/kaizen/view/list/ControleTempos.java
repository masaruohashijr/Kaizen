package com.logus.kaizen.view.list;

public class ControleTempos {

	private boolean controlaTempo = false;
	private boolean chronosAtivo = false;
	public ControleTempos() {}
	public ControleTempos(boolean controlaTempo, boolean chronosAtivo) {
		this.controlaTempo = controlaTempo;
		this.chronosAtivo = chronosAtivo;
	}
	public boolean isControlaTempo() {
		return controlaTempo;
	}
	public void setControlaTempo(boolean controlaTempo) {
		this.controlaTempo = controlaTempo;
	}
	public boolean isChronosAtivo() {
		return chronosAtivo;
	}
	public void setChronosAtivo(boolean chronosAtivo) {
		this.chronosAtivo = chronosAtivo;
	}

}
