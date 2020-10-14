package dad.javafx.calculadoracompleja;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class Aplicacion extends Application {
	Complejo complejo1 = new Complejo();
	Complejo complejo2 = new Complejo();
	Complejo complejoRes = new Complejo();

	private ComboBox<String> operacionCombo;
	private TextField num11Text, num12Text, num21Text, num22Text, num31Text, num32Text;
	private Label num11Label, num12Label, num21Label, num22Label, num31Label, num32Label;
	private Separator separador;

	public void start(Stage primaryStage) throws Exception {

		// Primer Numero
		HBox num1Box = new HBox(5);

		num11Text = new TextField("0");
		num11Text.setPrefColumnCount(3);
		num11Text.setAlignment(Pos.CENTER);
		num11Label = new Label("+");
		num12Text = new TextField("0");
		num12Text.setPrefColumnCount(3);
		num12Text.setAlignment(Pos.CENTER);
		num12Label = new Label("i");

		num1Box.getChildren().addAll(num11Text, num11Label, num12Text, num12Label);
		num1Box.setSpacing(5);
		num1Box.setAlignment(Pos.CENTER);

		// Segundo Numero
		HBox num2Box = new HBox();

		num21Text = new TextField("0");
		num21Text.setPrefColumnCount(3);
		num21Text.setAlignment(Pos.CENTER);
		num21Label = new Label("+");
		num22Text = new TextField("0");
		num22Text.setPrefColumnCount(3);
		num22Text.setAlignment(Pos.CENTER);
		num22Label = new Label("i");

		num2Box.getChildren().addAll(num21Text, num21Label, num22Text, num22Label);
		num2Box.setAlignment(Pos.CENTER);
		num2Box.setSpacing(5);

		// Separador
		separador = new Separator();

		// Numero Resultado
		HBox resultadoBox = new HBox(5);

		num31Text = new TextField("0");
		num31Text.setPrefColumnCount(3);
		num31Text.setDisable(true);
		num31Text.setAlignment(Pos.CENTER);
		num31Label = new Label("+");
		num32Text = new TextField("0");
		num32Text.setPrefColumnCount(3);
		num32Text.setDisable(true);
		num32Text.setAlignment(Pos.CENTER);
		num32Label = new Label("i");

		resultadoBox.getChildren().addAll(num31Text, num31Label, num32Text, num32Label);
		resultadoBox.setSpacing(5);
		resultadoBox.setAlignment(Pos.CENTER);

		// VBox de los input y output
		VBox inputnOutputBox = new VBox(5);
		inputnOutputBox.getChildren().addAll(num1Box, num2Box, separador, resultadoBox);
		inputnOutputBox.setAlignment(Pos.CENTER);

		// Tipo de operacion
		operacionCombo = new ComboBox<>();
		operacionCombo.getItems().addAll("+", "-", "*", "/");
		// operacionCombo.getSelectionModel().select(0);
		// operacionCombo.setValue("+");

		// VBox que contiene la operacion
		VBox operacionBox = new VBox(5);
		operacionBox.getChildren().add(operacionCombo);
		operacionBox.setAlignment(Pos.CENTER);

		// Caja Principal
		HBox cajaPrincipalBox = new HBox(5);
		cajaPrincipalBox.getChildren().addAll(operacionBox, inputnOutputBox);
		cajaPrincipalBox.setAlignment(Pos.CENTER);

		// Escena
		Scene escena = new Scene(cajaPrincipalBox, 320, 200);
		primaryStage.setScene(escena);
		primaryStage.setTitle("Calculadora de numeros Complejos");
		primaryStage.show();

		// Binding primer número
		num11Text.textProperty().bindBidirectional(complejo1.realProperty(), new NumberStringConverter());
		num12Text.textProperty().bindBidirectional(complejo1.imaginarioProperty(), new NumberStringConverter());

		// Binding segundo número
		num21Text.textProperty().bindBidirectional(complejo2.realProperty(), new NumberStringConverter());
		num22Text.textProperty().bindBidirectional(complejo2.imaginarioProperty(), new NumberStringConverter());

		// Binding número resultado
		num31Text.textProperty().bind(complejoRes.realProperty().asString("%.2f"));
		num32Text.textProperty().bind(complejoRes.imaginarioProperty().asString("%.2f"));

		// Listener operación
		operacionCombo.valueProperty().addListener(e -> {
			
			// Caso suma
			if (operacionCombo.valueProperty().getValue() == "+") {
				suma();
			}

			// Caso resta
			else if (operacionCombo.valueProperty().getValue() == "-") {
				resta();
			}

			// Caso multiplicación
			else if (operacionCombo.valueProperty().getValue() == "*") {
				multiplicacion();
			}

			// Caso división
			else if (operacionCombo.valueProperty().getValue() == "/") {
				division();
			}
		});

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void suma() {

		complejoRes.realProperty().bind(complejo1.realProperty().add(complejo2.realProperty()));
		
		complejoRes.imaginarioProperty().bind(complejo1.imaginarioProperty().add(complejo2.imaginarioProperty()));
		
	}

	public void resta() {

		complejoRes.realProperty().bind(complejo1.realProperty().subtract(complejo2.realProperty()));

		complejoRes.imaginarioProperty().bind(complejo1.imaginarioProperty().subtract(complejo2.imaginarioProperty()));
		
	}

	public void multiplicacion() {

		complejoRes.realProperty().bind(complejo1.realProperty().multiply(complejo2.realProperty())
				.subtract(complejo1.imaginarioProperty().multiply(complejo2.imaginarioProperty())));

		complejoRes.imaginarioProperty().bind(complejo1.realProperty().multiply(complejo2.imaginarioProperty())
				.add(complejo2.realProperty().multiply(complejo1.imaginarioProperty())));
		
	}

	public void division() {

		complejoRes.realProperty()
				.bind(((complejo1.realProperty().multiply(complejo2.realProperty())
						.add(complejo1.imaginarioProperty().multiply(complejo2.imaginarioProperty())))
								.divide(complejo2.realProperty().multiply(complejo2.realProperty())
										.add(complejo2.realProperty().multiply(complejo2.realProperty())))));

		complejoRes.imaginarioProperty()
				.bind(((complejo2.realProperty().multiply(complejo1.imaginarioProperty())
						.subtract(complejo1.realProperty().multiply(complejo2.imaginarioProperty())))
								.divide(complejo2.realProperty().multiply(complejo2.realProperty())
										.add(complejo2.realProperty().multiply(complejo2.realProperty())))));
	}
}
