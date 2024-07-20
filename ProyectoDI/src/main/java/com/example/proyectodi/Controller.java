package com.example.proyectodi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.help.HelpSetException;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Pane eliminarTareaPane;
    @FXML
    private Button cancelarEliminarTareaB;
    @FXML
    private Button eliminarTareaFinalB;
    @FXML
    private Button eliminarTareaB;
    @FXML
    private Button editarTareaBConfirmar;
    @FXML
    private Button editarCancelarTareaB;
    @FXML
    private TextField editarEstadoTF;
    @FXML
    private TextField buscador;
    @FXML
    private DatePicker editarFechaFDP;

    @FXML
    private TextField editarNombreTareaTF;

    @FXML
    private ChoiceBox<String> editarPrioCB;

    @FXML
    private Button editarTareaB;

    @FXML
    private Pane editarTareaPane;
    @FXML
    private TextField nuevoNombreProyectoTextField;
    @FXML
    private Button nuevoProyectoB;

    @FXML
    private Pane nuevoProyectoPane;

    @FXML
    private Button aceptarProyctoB;
    @FXML
    private Button cancelarProyectoB;
    @FXML
    private DatePicker fechaInicioProyecto;

    @FXML
    private Button cancelarTareaB;
    @FXML
    private Button guardarNuevaTarea;

    @FXML
    private Button borrarProyectoB;

    @FXML
    private ChoiceBox<String> choicePrio;

    @FXML
    private ChoiceBox<String> choiceProyecto;

    @FXML
    private TableColumn<?, ?> columnEstado;

    @FXML
    private TableColumn<?, ?> columnFechaF;

    @FXML
    private TableColumn<?, ?> columnFechaI;

    @FXML
    private TableColumn<?, ?> columnID;

    @FXML
    private TableColumn<?, ?> columnNombre;

    @FXML
    private TableColumn<?, ?> columnPrio;

    @FXML
    private DatePicker fechaFinalTarea;

    @FXML
    private Button crearProyectoB;

    @FXML
    private Label fechaFinalLabel;

    @FXML
    private Label fechaInicioLabel;

    @FXML
    private Label fechaInicioLabel1;

    @FXML
    private Label fechaInicioLabel111;


    @FXML
    private DatePicker fechaInicioTarea;

    @FXML
    private Label nombreProyectoLabel;

    @FXML
    private TextField nombreProyectoTextField;

    @FXML
    private Button nuevaTareaB;

    @FXML
    private Pane nuevaTareaPane;

    @FXML
    private TextField nuevaTareaTextField;


    @FXML
    private TableView<Tarea> tblTareas;

    private ObservableList<Tarea> tareas;
    @FXML
    private ImageView imagen;
    @FXML
    private Pane infoPane;

    @FXML
    private Button bAyuda;

    @FXML
    private Button informeButton;
    private ReportGenerator rg;
    @FXML
    void crearProyecto(MouseEvent event) {
        if(!nuevoProyectoPane.isVisible())
            nuevaTareaPane.setVisible(true);
    }

    @FXML
    void cancelarProyecto(MouseEvent event) {
        if(nuevoProyectoPane.isVisible())
            nuevoProyectoPane.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tareas=FXCollections.observableArrayList();
        choiceProyecto.getItems().addAll(nombresProyectos());
        choicePrio.getItems().addAll("ALTA","MEDIA","BAJA","URGENTE");
        editarPrioCB.getItems().addAll("ALTA","MEDIA","BAJA","URGENTE");
        nuevaTareaPane.setVisible(false);
        nuevoProyectoPane.setVisible(false);
        editarTareaPane.setVisible(false);
        eliminarTareaPane.setVisible(false);
        infoPane.setVisible(false);
        rg= new ReportGenerator();

        this.columnID.setCellValueFactory(new PropertyValueFactory("id"));
        this.columnNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.columnFechaI.setCellValueFactory(new PropertyValueFactory("fechaI"));
        this.columnFechaF.setCellValueFactory(new PropertyValueFactory("fechaF"));
        this.columnPrio.setCellValueFactory(new PropertyValueFactory("prioridad"));
        this.columnEstado.setCellValueFactory(new PropertyValueFactory("estado"));
    }

    public void mostrarTareas(){
        tareas=FXCollections.observableArrayList();
        DAO dao= new DAO();
        String nombreProyecto=choiceProyecto.getValue();

        int idProyecto= dao.obtenerUnProyecto(nombreProyecto);

        Proyecto p= dao.obtenerUnProyectoAPartirDelID(idProyecto);

        List<Tarea> listaTareas= dao.obtenerTareas(p);

        for (Tarea t: listaTareas) {
            if(!tareas.contains(t)){
                tareas.add(t);
            }
        }
        tblTareas.setItems(tareas);
    }

    public void mostrarTareasFiltradas(String nombre){
        tareas=FXCollections.observableArrayList();
        DAO dao= new DAO();
        String nombreProyecto=choiceProyecto.getValue();

        int idProyecto= dao.obtenerUnProyecto(nombreProyecto);

        Proyecto p= dao.obtenerUnProyectoAPartirDelID(idProyecto);

        List<Tarea> listaTareas= dao.obtenerTareas(p);

        for (Tarea t: listaTareas) {
                if(!tareas.contains(t)){
                if(t.getNombre().contains(nombre))
                    tareas.add(t);
            }
        }
        tblTareas.setItems(tareas);
    }

    public List<Proyecto> formarProyectos(){
        DAO d= new DAO();
        List<Proyecto> lp=d.obtenerProyectos();
        return lp;
    }

    public List<String> nombresProyectos(){
        List<String> nombres= new LinkedList<>();
        DAO d= new DAO();
        List<Proyecto> lp=d.obtenerProyectos();
        for (Proyecto p:lp
             ) {
            nombres.add(p.getNombre());
        }
        return nombres;
    }

    @FXML
    void cambiarNombreProyecto(MouseEvent event) {
        String n= choiceProyecto.getValue();
        nombreProyectoLabel.setText(n);
    }

    @FXML
    void formar(MouseEvent event) {
        DAO d= new DAO();
        String n= choiceProyecto.getValue();
        nombreProyectoLabel.setText(n);
        List<Proyecto> lp=d.obtenerProyectos();

        for (Proyecto p:lp
             ) {
            if(p.getNombre().equals(n)) {
                fechaInicioLabel.setText(p.getFechaI());
                fechaFinalLabel.setText(p.getFechaF());
            }
        }

        mostrarTareas();
    }

    @FXML
    void nuevaTarea(MouseEvent event) {
        //abrir panel para crear tarea
        nuevaTareaPane.setVisible(true);
    }

    @FXML
    void guardarNuevaTareaMetodo(MouseEvent event) {
        String nombre=nuevaTareaTextField.getText();
        LocalDate date= fechaInicioTarea.getValue();
        String fechaI= date.toString();
        date=fechaFinalTarea.getValue();
        String fechaF=date.toString();

        DAO d= new DAO();
        int id= d.obtenerUnProyecto(choiceProyecto.getValue());
        d.crearTarea(nombre,fechaI,fechaF,Prioridad.valueOf(choicePrio.getValue()),"ESPERA",id);
        nuevaTareaPane.setVisible(false);
    }

    @FXML
    void cancelarTareaM(MouseEvent event) {
        if (nuevaTareaPane.isVisible())
            nuevaTareaPane.setVisible(false);
    }


    @FXML
    void abrirPanelNuevoProyecto(MouseEvent event) {
        if(!nuevoProyectoPane.isVisible()){
            nuevoProyectoPane.setVisible(true);
        }
    }

    @FXML
    void cerrarProyectoBotonCancelar(MouseEvent event) {
        if(nuevoProyectoPane.isVisible()){
            nuevoProyectoPane.setVisible(false);
        }
    }

    @FXML
    void crearNuevoProyectoM(MouseEvent event) {
        String nombre= nuevoNombreProyectoTextField.getText();
        LocalDate d= fechaInicioProyecto.getValue();
        String fechaI= d.toString();

        DAO dao= new DAO();
        dao.crearProyecto(nombre,fechaI);

        if(nuevoProyectoPane.isVisible())
            nuevoProyectoPane.setVisible(false);

        choiceProyecto.getItems().add(nombre);
    }

    @FXML
    void abrirPanelEditarTarea(MouseEvent event) {
        if(!editarTareaPane.isVisible()){
            editarTareaPane.setVisible(true);
        }

        Tarea t= tblTareas.getSelectionModel().getSelectedItem();

        editarNombreTareaTF.setText(t.getNombre());
        String fecha= t.getFechaF();

        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date= LocalDate.parse(fecha,formatter);

        editarFechaFDP.setValue(date);
        editarPrioCB.setValue(t.getPrioridad().toString());

        editarEstadoTF.setText(t.getEstado());
    }

    @FXML
    void editarCancelarEditarTareaM(MouseEvent event) {
        if (editarTareaPane.isVisible()){
            editarTareaPane.setVisible(false);
        }
    }

    @FXML
    void editarTareaM(MouseEvent event) {
        Tarea t= tblTareas.getSelectionModel().getSelectedItem();
        String nombre= editarNombreTareaTF.getText();
        String fechaF= String.valueOf(editarFechaFDP.getValue());
        String prio= editarPrioCB.getValue();
        String estado= editarEstadoTF.getText();

        DAO dao= new DAO();

        dao.actualizarTarea(t,nombre,fechaF,prio,estado);

        if(editarTareaPane.isVisible()){
            editarTareaPane.setVisible(false);
        }

    }

    @FXML
    void abrirEliminarTareaPane(MouseEvent event) {
        if(!eliminarTareaPane.isVisible())
            eliminarTareaPane.setVisible(true);
    }

    @FXML
    void cancelarEliminarTareaM(MouseEvent event) {
        if(eliminarTareaPane.isVisible())
            eliminarTareaPane.setVisible(false);
    }

    @FXML
    void eliminarTareaFinalM(MouseEvent event) {
        Tarea t= tblTareas.getSelectionModel().getSelectedItem();
        DAO dao= new DAO();
        dao.eliminarTarea(t);

        if(eliminarTareaPane.isVisible())
            eliminarTareaPane.setVisible(false);
    }

    @FXML
    void buscarTareas(KeyEvent event) {
        String b= buscador.getText();
        mostrarTareasFiltradas(b);
    }

    @FXML
    void abrirInfoApp(MouseEvent event) {
        if(!infoPane.isVisible())
            infoPane.setVisible(true);
        else
            infoPane.setVisible(false);
    }


    @FXML
    void generarReport(ActionEvent event) {
        rg.generarInforme();
    }

    @FXML
    void ayuda(ActionEvent event) {
        try{
            URL helpURL= this.getClass().getResource("/help/help_set.hs");
            ClassLoader classLoader = this.getClass().getClassLoader();
            HelpSet helpSet= new HelpSet(classLoader,helpURL);
            HelpBroker hb= helpSet.createHelpBroker();
            hb.setDisplayed(true);
        } catch (HelpSetException e) {
            e.printStackTrace();
        }
    }


}
