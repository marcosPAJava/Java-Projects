public enum AcaoComboGestao {

	AM("Aprovar Meta"), CM("Cancelar Meta"), EO("Enviar Observa\u00E7\u00E3o"), VM("Validar Meta"), EM("Excluir Meta");

	private AcaoComboGestao(String value) {
		this.value = value;
	}

	final String value;

	public String getValue() {
		return value;
	}

}
