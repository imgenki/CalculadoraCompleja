package dad.javafx.calculadoracompleja;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Complejo {

	private DoubleProperty real = new SimpleDoubleProperty(0);
	private DoubleProperty imaginario = new SimpleDoubleProperty(0);

	public Complejo() {
	}

	public Complejo(double real, double imaginario) {
		super();
		setReal(real);
		setImaginario(imaginario);
	}

	public final DoubleProperty realProperty() {
		return this.real;
	}

	public final double getReal() {
		return this.realProperty().get();
	}

	public final void setReal(final double real) {
		this.realProperty().set(real);
	}

	public final DoubleProperty imaginarioProperty() {
		return this.imaginario;
	}

	public final double getImaginario() {
		return this.imaginarioProperty().get();
	}

	public final void setImaginario(final double imaginario) {
		this.imaginarioProperty().set(imaginario);
	}
}
