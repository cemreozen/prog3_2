public interface EndlicherAutomat {
	/**
	 *
	 * @return
	 */
	boolean isAkzeptierend();

	/**
	 *
	 */
	void wechselZustand();

	/**
	 *
	 */
	void startAutomat(String zeichenkette);

	/**
	 *
	 * @param zeichenkette
	 * @return
	 */
	default boolean testen(String zeichenkette) {
		startAutomat(zeichenkette);
		wechselZustand();
		return (isAkzeptierend());
	}
}
