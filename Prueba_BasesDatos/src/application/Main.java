package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader vistaDatos = new FXMLLoader (getClass().getResource("/application/SensoresPrueba.fxml"));
			sensoresPrueba control= new sensoresPrueba();
			vistaDatos.setController(control);
			Parent root= vistaDatos.load();
			control.setFecha();
			control.setNombrePaciente("Jose Ignacio");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
