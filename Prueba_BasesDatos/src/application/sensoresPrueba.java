package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class sensoresPrueba {

    @FXML
    private DatePicker fecha;

    @FXML
    private Button temperaturaCorporal;

    @FXML
    private Label nombrePaciente;

    @FXML
    private Button ritmoCardiaco;

    @FXML
    private Button nivelSangre;

    @FXML
    void mostrarRitmoCardiaco(ActionEvent event) {
    	verDatos(ritmoCardiaco.getText());
    }

    @FXML
    void mostrarTemperatura(ActionEvent event) {
    	//int id = encontrarPaciente(nombrePaciente.getText());
    	verDatos(temperaturaCorporal.getText());
    }

    @FXML
    void mostrarNivelOxigeno(ActionEvent event) {

    }
    
    public void setNombrePaciente(String nombre) {
    	nombrePaciente.setText(nombre);
		nombrePaciente.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-weight: bold");
    }
    
    public int encontrarPaciente(String nombre) {
    	int id_paciente = 0;
    	String user = "root";
    	String password = "joseIgnacio1";
    	try {
			Connection conexion = DriverManager.getConnection("jdbc:mariadb://127.0.0.1/prueba sensores?user=" + user + "&password=" + password);
			ResultSet resultados = conexion.createStatement().executeQuery("SELECT id_paciente FROM paciente WHERE nombre =" + "'" + nombre + "'");
			while(resultados.next()) {
				id_paciente = resultados.getInt("id_paciente");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return id_paciente;
    }
    
    public void verDatos(String tipo) {
    	String user = "root";
    	String password = "joseIgnacio1";
    	try {
			Connection conexion = DriverManager.getConnection("jdbc:mariadb://127.0.0.1/prueba sensores?user=" + user + "&password=" + password);
			ResultSet resultados = conexion.createStatement().executeQuery("SELECT nombre, dato, instante FROM paciente, sensor WHERE sensor.id_paciente = paciente.id_paciente AND tipo =" + "'" + tipo + "'");
			while(resultados.next()) {
				if(fecha.getValue().toString().equals(resultados.getDate("instante").toString())) {
					System.out.println("Nombre: " + resultados.getString("nombre") + ", Dato: " + resultados.getFloat("dato") + " " + resultados.getTime("instante"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    public void setFecha() {
    	LocalDate fechaHoy = LocalDate.now();
    	fecha.setValue(fechaHoy);
    }

}
