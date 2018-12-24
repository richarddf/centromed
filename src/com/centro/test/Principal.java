/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.centro.test;

import com.centro.model.Medico;
import com.centro.model.ObraSocial;
import com.centro.model.Paciente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Principal {

    public static Date horaTurno;
    
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/dbcmed?zeroDateTimeBehavior=convertToNull";
        String username = "root";
        //String pass = "";
        String pass = "";
        
        Scanner sc = new Scanner(System.in);
        Scanner waitForKeypress = new Scanner(System.in);
        
        String opcion = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Class.forName("org.gjt.mm.mysql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de MySQL: " + ex);
        }

        //TODO: Clear Screen
        do {
            System.out.println(">>---- Menu Pacientes ----<<");
            System.out.println(rPad("",50,'*'));
            System.out.println("1.- Agregar Paciente");
            System.out.println("2.- Modificar Paciente");
            System.out.println("3.- Eliminar Paciente");
            System.out.println("4.- Mostrar Pacientes");
            System.out.println("5.- Mostrar Historia Clinica");            
            System.out.println(rPad("",50,'*'));

            System.out.println(">>---- Menu Obras Sociales ----<<");
            System.out.println(rPad("",50,'*'));
            System.out.println("6.- Agregar Obra Social");
            System.out.println("7.- Modificar Obra Social");
            System.out.println("8.- Eliminar Obra Social");
            System.out.println("9.- Mostrar Obras Sociales");
            System.out.println(rPad("",50,'*'));

            System.out.println(">>---- Menu Médicos ----<<");
            System.out.println(rPad("",50,'*'));
            System.out.println("10.- Agregar Médico");
            System.out.println("11.- Modificar Médico");
            System.out.println("12.- Eliminar Médico");
            System.out.println("13.- Mostrar Médico");
            System.out.println(rPad("",50,'*'));

            System.out.println(">>---- Menu Turnos ----<<");
            System.out.println(rPad("",50,'*'));
            System.out.println("14.- Agregar Turno");
            //System.out.println("15.- Modificar Turno");
            System.out.println("15.- Eliminar Turno");
            System.out.println("16.- Mostrar Turnos Por Medico");
            System.out.println("17.- Mostrar Turnos Por Fecha");
            System.out.println(rPad("",50,'*'));
            
            System.out.println("s.- Salida\n");
            
            System.out.print("Seleccione una opcion: ");
            opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    agregaPaciente(url, username, pass, sc);
                    waitForKeypress.nextLine();
                    break;
                case "2":
                    modificaPaciente(url, username, pass, sc);
                    waitForKeypress.nextLine();
                    break;
                case "3":
                    eliminaPaciente(url, username, pass, sc);
                    waitForKeypress.nextLine();
                    break;
                case "4":
                    mostrarPacientes(url, username, pass, sc);
                    waitForKeypress.nextLine();
                    break;
                case "5":
                    mostrarHistoriaClinica(url, username, pass);
                    waitForKeypress.nextLine();
                    break;
                case "6":
                    agregaObraSocial(url, username, pass, sc);
                    waitForKeypress.nextLine();
                    break;
                case "7":
                    modificaObraSocial(url, username, pass, sc);
                    waitForKeypress.nextLine();
                    break;
                case "8":
                    eliminaObraSocial(url, username, pass, sc);
                    waitForKeypress.nextLine();
                    break;
                case "9":
                    mostrarObraSocial(url, username, pass, sc);
                    waitForKeypress.nextLine();
                    break;
                case "10":
                    agregaMedico(url, username, pass, sc);
                    waitForKeypress.nextLine();
                    break;
                case "11":
                    modificaMedico(url, username, pass, sc);
                    waitForKeypress.nextLine();
                    break;
                case "12":
                    eliminaMedico(url, username, pass, sc);
                    waitForKeypress.nextLine();
                    break;
                case "13":
                    mostrarMedicos(url, username, pass, sc);
                    waitForKeypress.nextLine();
                    break;
                case "14":
                    agregaTurno(url, username, pass, sc);
                    waitForKeypress.nextLine();
                    break;
                case "15":
                    eliminaTurno(url, username, pass, sc);
                    //Date hora = validarHora("Turno");
                    waitForKeypress.nextLine();
                    break;                
                case "16":
                    mostrarTurnoMedico(url, username, pass, sc);
                    waitForKeypress.nextLine();
                    break;
                case "17":
                    mostrarTurnoFecha(url, username, pass, sc);
                    waitForKeypress.nextLine();
                    break;
                case "s":
                case "S":
                    break;
                default:
                    System.out.println("La opcion ingresada es incorrecta, seleccione una nueva opcion");
                    break;
            }
            borrarPantalla();
        } while (!opcion.equalsIgnoreCase("s"));        // TODO code application logic here
        
    }
    
    public static void borrarPantalla() {
        for (int clear = 0; clear < 200; clear++) {
            System.out.println("\b");
        }
    }
  
    public static String lPad(String str, Integer length, char car) {

        if (str == null) {
            str = "";
        }
        return str
             + 
             String.format("%" + (length - str.length()) + "s", "")
                         .replace(" ", String.valueOf(car));
    }

    public static String rPad(String str, Integer length, char car) {
      
        if (str == null) {
            str = "";
        }
        return String.format("%" + (length - str.length()) + "s", "")
                   .replace(" ", String.valueOf(car)) 
             +
             str;
    }
 
    public static void agregaPaciente(String url, String username, String pass, Scanner sc){
        //Paciente pac;
        borrarPantalla();
        System.out.println("Agregar Paciente");
        System.out.println("------------------------------------\n");
        String apellidoYNombre = validarNombrePropio("el Apellido y Nombre del paciente");
        System.out.print("Ingrese el domicilio: ");
        String domicilio = sc.nextLine();
        String tipoDocumento = validarTipoDoc();
        String numeroDocumento = validarDocumento("el numero de documento del paciente");
        Date fechaNac = validarFecha("Nacimiento");
        System.out.print("Ingrese la nacionalidad ");
        String nacionalidad = sc.nextLine();
        System.out.print("Ingrese el numero de telefono: ");
        String numeroTelefono = sc.nextLine();
        String sexo = validarSexo();
        System.out.print("Ingrese el email: ");
        String email = sc.nextLine();
        int obraSoc = buscarIdObraSocial(url, username, pass);
        System.out.print("Ingrese Codigo de Afiliado de la Obra Social: ");
        String codigoAfilOS = sc.nextLine();
        System.out.print("Ingrese el plan en la Obra Social: ");
        String planOS = sc.nextLine();
        int newIdPac = getNewId(url, username, pass, "paciente","IdPaciente");
        
        try (Connection con = DriverManager.getConnection(url, username, pass)) {
            Statement stmt = con.createStatement();
           
            String query = "INSERT INTO PACIENTE VALUES (" + newIdPac + ","
                    + "'" + apellidoYNombre + "', '" + domicilio + "', '" +tipoDocumento+"', "
                    + numeroDocumento + ", '" + new java.sql.Date(fechaNac.getTime()) + "', " 
                    + "'" + nacionalidad+ "', '" + numeroTelefono + "', '" + sexo + "', '" + email + "', "
                    + obraSoc + ", '" + codigoAfilOS +"', "
                    + "'" + planOS + "')";           
           
            if (stmt.executeUpdate(query) == 1) {
                System.out.println("El paciente fue añadido al sistema con exito. Presione Enter para continuar.");
                agregaHistoriaClinica(url, username, pass, newIdPac, apellidoYNombre);
            } else {
                System.out.println("Fallo al insertar paciente!");
            }

        } catch (SQLException e) {
            System.out.println("Código de Error: " + e.getErrorCode() + "\n" +
              "SLQState: " + e.getSQLState() + "\n" +
              "Mensaje: " + e.getMessage() + "\n");            
            System.out.println("Presione Enter para continuar.");
        }        
    }

    public static void modificaPaciente(String url, String username, String pass, Scanner sc){

        borrarPantalla();
        System.out.println("Modificar Paciente");
        System.out.println("------------------------------------\n");
        Paciente pac = buscarPaciente(url, username, pass);
        pac.mostrarPaciente();
        //int idpac = pac.getIdPersona();
        
        if(siNo("Desea modificar datos del paciente")){
            System.out.println("Modificando datos...");
            String apellidoYNombre = validarNombrePropio("el Apellido y Nombre del paciente");
            System.out.print("Ingrese el domicilio: ");
            String domicilio = sc.nextLine();
            String tipoDocumento = validarTipoDoc();
            String numeroDocumento = validarDocumento("el numero de documento del paciente");
            Date fechaNac = validarFecha("Nacimiento");
            System.out.print("Ingrese la nacionalidad ");
            String nacionalidad = sc.nextLine();
            System.out.print("Ingrese el numero de telefono: ");
            String numeroTelefono = sc.nextLine();
            String sexo = validarSexo();
            System.out.print("Ingrese el email: ");
            String email = sc.nextLine();
            int obraSoc = buscarIdObraSocial(url, username, pass);
            System.out.print("Ingrese Codigo de Afiliado de la Obra Social: ");
            String codigoAfilOS = sc.nextLine();
            System.out.print("Ingrese el plan en la Obra Social: ");
            String planOS = sc.nextLine();
                    
            try (Connection con = DriverManager.getConnection(url, username, pass)) {
                Statement stmt = con.createStatement();

                String query = "UPDATE PACIENTE SET apellidoynombre = '" + apellidoYNombre + "', domicilio = '" + domicilio + "', tipodocumento = '" +tipoDocumento+"',"
                    + " numerodocumento ="+numeroDocumento + ", fechanacimiento = '" + new java.sql.Date(fechaNac.getTime()) + "', nacionalidad = '"+ nacionalidad+ "',"
                    + " telefono = '"+ numeroTelefono + "', sexo = '" + sexo + "', email = '" + email + "', "
                    + " idobrasocial ="+obraSoc + ", numeroafiliadoObraSocial = '" + codigoAfilOS +"', nombrePlanObraSocial = '" + planOS + "' WHERE IdPaciente = " + pac.getIdPersona();                
       
                if (stmt.executeUpdate(query) == 1) {
                    System.out.println("El paciente fue modificado con exito. Presione Enter para continuar.");
                }
            } catch (SQLException e) {
                System.out.println("Código de Error: " + e.getErrorCode() + "\n" +
                  "SLQState: " + e.getSQLState() + "\n" +
                  "Mensaje: " + e.getMessage() + "\n");            
                System.out.println("Presione Enter para continuar.");
            }
            
        }else{
            System.out.println("Presione Enter para continuar.");
        }       
    }
    
    public static void eliminaPaciente(String url, String username, String pass, Scanner sc){
        //Paciente pac;
        borrarPantalla();
        System.out.println("Eliminar Paciente");
        System.out.println("------------------------------------\n");
        int idpac = buscarIdPaciente(url, username, pass);

        if(siNo("Desea eliminar el paciente y su historia clinica")){
            try (Connection con = DriverManager.getConnection(url, username, pass)) {
                Statement stmt = con.createStatement();

                String query = "DELETE FROM PACIENTE WHERE IdPaciente = " + idpac;
                String query2 = "DELETE FROM HISTORIACLINICAPACIENTE WHERE IdPaciente = " + idpac;
                
                if (stmt.executeUpdate(query) == 1) {
                    System.out.println("El paciente fue eliminado del sistema con exito. Presione Enter para continuar.");
                }
                if (stmt.executeUpdate(query2) == 1) {
                    System.out.println("La historia clinica del paciente fue eliminada del sistema con exito.");
                }

            } catch (SQLException e) {
                System.out.println("Código de Error: " + e.getErrorCode() + "\n" +
                  "SLQState: " + e.getSQLState() + "\n" +
                  "Mensaje: " + e.getMessage() + "\n");
                System.out.println("Presione Enter para continuar.");
                //System.out.println("Excepcion creando la conexión: " + e);
                //System.exit(0);
            }            
        }else{
            System.out.println("Presione Enter para continuar.");
        }       
    }

    public static void agregaHistoriaClinica(String url, String username, String pass, int idPac, String nomPac){
        String historiaClin = "Historia Clinica: "+idPac+" "+nomPac;
        try (Connection con = DriverManager.getConnection(url, username, pass)) {
            Statement stmt = con.createStatement();
           
            String query = "INSERT INTO HISTORIACLINICAPACIENTE VALUES (" + idPac + ","
                    + "'" + historiaClin + "')";           
           
            if (stmt.executeUpdate(query) == 1) {
                //System.out.println("El paciente fue añadido al sistema con exito. Presione Enter para continuar.");
            } else {
                System.out.println("Fallo al insertar Historia Clinica!");
            }

        } catch (SQLException e) {
            System.out.println("Código de Error: " + e.getErrorCode() + "\n" +
              "SLQState: " + e.getSQLState() + "\n" +
              "Mensaje: " + e.getMessage() + "\n");            
            System.out.println("Presione Enter para continuar.");
        }        
    }
    
    public static void mostrarPacientes(String url, String username, String pass, Scanner sc){
        String query = "SELECT apellidoYNombre,paciente.Domicilio,numeroDocumento,fechanacimiento,paciente.telefono,razonsocial FROM paciente"
                +" INNER JOIN obrasocial on paciente.idobrasocial = obrasocial.idobrasocial";
        borrarPantalla();
        System.out.println("Lista de Pacientes");
        System.out.println(rPad("",180,'-'));
        System.out.println(lPad("Apellido Y Nombre",40,' ')+lPad("Domicilio",50,' ')+rPad("Documento",10,' ')+rPad("Fecha Nac",20,' ')+"\t"+lPad("Telefono",20,' ')+lPad("Obra Social",30,' '));
        System.out.println(rPad("",180,'-'));

        try (Connection con = DriverManager.getConnection(url, username, pass)) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                
                String apenom = rs.getString("apellidoYNombre");
                String domic = rs.getString("domicilio");
                String doc = rs.getString("numeroDocumento");
                Date fecnac = rs.getDate("fechanacimiento");
                String fecha = obtenerFechaFormateada(fecnac,"dd/MM/yyyy");
                String tel = rs.getString("telefono");
                String rsocial = rs.getString("razonsocial");

                System.out.println(lPad(apenom.trim(),40,' ')+lPad(domic.trim(),50,' ')+rPad(doc.trim(),10,' ')+rPad(fecha.trim(),20,' ')+"\t"+lPad(tel.trim(),20,' ')+lPad(rsocial.trim(),30,' '));
            }
            System.out.println("\nPresione Enter para continuar.");

        } catch (SQLException e) {
            System.out.println("Código de Error: " + e.getErrorCode() + "\n" +
              "SLQState: " + e.getSQLState() + "\n" +
              "Mensaje: " + e.getMessage() + "\n");            
            System.out.println("Presione Enter para continuar.");
        }        
    }

    public static void mostrarHistoriaClinica(String url, String username, String pass){
        borrarPantalla();
        System.out.println("Historia Clinica");
        System.out.println("------------------------------------\n");
        int idpac = buscarIdPaciente(url, username, pass);

        try (Connection con = DriverManager.getConnection(url, username, pass)) {
            Statement stmt = con.createStatement();

            String query = "SELECT * FROM HISTORIACLINICAPACIENTE WHERE IDPACIENTE = " + idpac;

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String contenido = rs.getString("contenido");                   
                System.out.println(contenido);
            }
            System.out.println("\nPresione Enter para continuar.");
        } catch (SQLException e) {
            System.out.println("Código de Error: " + e.getErrorCode() + "\n" +
              "SLQState: " + e.getSQLState() + "\n" +
              "Mensaje: " + e.getMessage() + "\n");            
            System.out.println("Presione Enter para continuar.");
        }       
    }
    
    public static void agregaMedico(String url, String username, String pass, Scanner sc){
        
        borrarPantalla();
        System.out.println("Agregar Médico");
        System.out.println("------------------------------------\n");
        String apellidoYNombre = validarNombrePropio("el Apellido y Nombre del Médico");
        System.out.print("Ingrese el domicilio: ");
        String domicilio = sc.nextLine();
        String tipoDocumento = validarTipoDoc();
        String numeroDocumento = validarDocumento("el numero de documento del médico");
        Date fechaNac = validarFecha("Nacimiento");
        System.out.print("Ingrese la nacionalidad ");
        String nacionalidad = sc.nextLine();
        System.out.print("Ingrese el numero de telefono: ");
        String numeroTelefono = sc.nextLine();
        String sexo = validarSexo();
        System.out.print("Ingrese el email: ");
        String email = sc.nextLine();
        System.out.print("Ingrese la Profesión: ");
        String profesion = sc.nextLine();        
        String cuit = validarCuit();
        System.out.print("Ingrese el numero de matrícula: ");
        String numeroMatricula = sc.nextLine();
        System.out.print("Ingrese la categoria ante el IVA: ");
        String categoriaIva = sc.nextLine();
        
        
        try (Connection con = DriverManager.getConnection(url, username, pass)) {
            Statement stmt = con.createStatement();
           
            String query = "INSERT INTO MEDICO VALUES (" + getNewId(url, username, pass, "medico", "IdMedico") + ","
                    + "'" + apellidoYNombre + "', '" + domicilio + "', '" +tipoDocumento+"', "
                    + numeroDocumento + ", '" + new java.sql.Date(fechaNac.getTime()) + "', " 
                    + "'" + nacionalidad+ "', '" + numeroTelefono + "', '" + sexo + "', '" + email + "', "
                    + "'" + profesion+ "', '" + cuit + "', " + numeroMatricula +", "
                    + "'" + categoriaIva + "')";           
           
            if (stmt.executeUpdate(query) == 1) {
                System.out.println("El medico fue añadido al sistema con exito. Presione Enter para continuar.");
                
            } else {
                System.out.println("Fallo al insertar Medico!");
            }

        } catch (SQLException e) {
            System.out.println("Código de Error: " + e.getErrorCode() + "\n" +
              "SLQState: " + e.getSQLState() + "\n" +
              "Mensaje: " + e.getMessage() + "\n");            
            System.out.println("Presione Enter para continuar.");
        }        
    }

    public static void modificaMedico(String url, String username, String pass, Scanner sc){
        borrarPantalla();
        System.out.println("Modificar Médico");
        System.out.println("------------------------------------\n");
        Medico med = buscarMedico(url, username, pass);
        med.mostrarMedico();
        
        if(siNo("Desea modificar datos del médico")){
            System.out.println("Modificando datos...");
            String apellidoYNombre = validarNombrePropio("el Apellido y Nombre del médico");
            System.out.print("Ingrese el domicilio: ");
            String domicilio = sc.nextLine();
            String tipoDocumento = validarTipoDoc();
            String numeroDocumento = validarDocumento("el numero de documento del médico");
            Date fechaNac = validarFecha("Nacimiento");
            System.out.print("Ingrese la nacionalidad: ");
            String nacionalidad = sc.nextLine();
            System.out.print("Ingrese el numero de telefono: ");
            String numeroTelefono = sc.nextLine();
            String sexo = validarSexo();
            System.out.print("Ingrese el email: ");
            String email = sc.nextLine();
            System.out.print("Ingrese la Profesión: ");
            String profesion = sc.nextLine();        
            String cuit = validarCuit();
            System.out.print("Ingrese el numero de matrícula: ");
            String numeroMatricula = sc.nextLine();
            System.out.print("Ingrese la categoria ante el IVA: ");
            String categoriaIva = sc.nextLine();            
                    
            try (Connection con = DriverManager.getConnection(url, username, pass)) {
                Statement stmt = con.createStatement();

                String query = "UPDATE MEDICO SET apellidoynombre = '" + apellidoYNombre + "', domicilio = '" + domicilio + "', tipodocumento = '" +tipoDocumento+"',"
                    + " numerodocumento ="+numeroDocumento + ", fechanacimiento = '" + new java.sql.Date(fechaNac.getTime()) + "', nacionalidad = '"+ nacionalidad+ "',"
                    + " telefono = '"+ numeroTelefono + "', sexo = '" + sexo + "', email = '" + email + "', "
                    + " profesion = '"+profesion + "', cuit = '" + cuit +"', numeroMatricula = " + numeroMatricula + ", categoriaIva = '"+categoriaIva+"' WHERE IdMedico = " + med.getIdPersona();                
       
                if (stmt.executeUpdate(query) == 1) {
                    System.out.println("El médico fue modificado con exito. Presione Enter para continuar.");
                }
            } catch (SQLException e) {
                System.out.println("Código de Error: " + e.getErrorCode() + "\n" +
                  "SLQState: " + e.getSQLState() + "\n" +
                  "Mensaje: " + e.getMessage() + "\n");
                System.out.println("\nPresione Enter para continuar.");
            }
            
        }else{
            System.out.println("Presione Enter para continuar.");
        }   
    }
    public static void eliminaMedico(String url, String username, String pass, Scanner sc){
        //Paciente pac;
        borrarPantalla();
        System.out.println("Eliminar Médico");
        System.out.println("------------------------------------\n");
        int idMed = buscarIdMedico(url, username, pass);

        if(siNo("Desea eliminar el médico")){
            try (Connection con = DriverManager.getConnection(url, username, pass)) {
                Statement stmt = con.createStatement();

                String query = "DELETE FROM MEDICO WHERE IdMedico = " + idMed;
                
                if (stmt.executeUpdate(query) == 1) {
                    System.out.println("El médico fue eliminado del sistema con exito. Presione Enter para continuar.");
                }
            } catch (SQLException e) {
                System.out.println("Código de Error: " + e.getErrorCode() + "\n" +
                  "SLQState: " + e.getSQLState() + "\n" +
                  "Mensaje: " + e.getMessage() + "\n");
                System.out.println("\nPresione Enter para continuar.");                
            }            
        }else{
            System.out.println("Presione Enter para continuar.");
        }       
    }    
    
    public static void mostrarMedicos(String url, String username, String pass, Scanner sc){
        String query = "SELECT idmedico,apellidoYNombre,Domicilio,numeroDocumento,fechanacimiento,telefono,profesion,cuit FROM medico";
        borrarPantalla();
        System.out.println("Lista de Médicos");
        System.out.println(rPad("",200,'-'));
        System.out.println(rPad("Codigo",10,' ')+"\t"+lPad("Apellido Y Nombre",40,' ')+lPad("Domicilio",50,' ')+rPad("Documento",10,' ')+rPad("Fecha Nac",20,' ')+"\t"+lPad("Telefono",20,' ')+lPad("Profesión",30,' ')+lPad("Cuit",15,' '));
        System.out.println(rPad("",200,'-'));

        try (Connection con = DriverManager.getConnection(url, username, pass)) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                
                String codigo = rs.getString("idmedico");
                String apenom = rs.getString("apellidoYNombre");
                String domic = rs.getString("domicilio");
                String doc = rs.getString("numeroDocumento");
                Date fechanac = rs.getDate("fechanacimiento");
                String fecha = obtenerFechaFormateada(fechanac,"dd/MM/yyyy");
                String tel = rs.getString("telefono");
                String profe = rs.getString("profesion");
                String cuit = rs.getString("cuit");

                System.out.println(rPad(codigo.trim(),10,' ')+"\t"+lPad(apenom.trim(),40,' ')+lPad(domic.trim(),50,' ')+rPad(doc.trim(),10,' ')+rPad(fecha.trim(),20,' ')+"\t"+lPad(tel.trim(),20,' ')+lPad(profe.trim(),30,' ')+lPad(cuit.trim(),15,' '));
            }
            System.out.println("\nPresione Enter para continuar.");
            
        } catch (SQLException e) {
            System.out.println("Código de Error: " + e.getErrorCode() + "\n" +
              "SLQState: " + e.getSQLState() + "\n" +
              "Mensaje: " + e.getMessage() + "\n");
            System.out.println("\nPresione Enter para continuar.");
        }        
    }

    public static void agregaObraSocial(String url, String username, String pass, Scanner sc){
        
        borrarPantalla();
        System.out.println("Agregar Obra Social");
        System.out.println("------------------------------------\n");
        String razonSocial = validarNombrePropio("la Razón Social");
        System.out.print("Ingrese el domicilio: ");
        String domicilio = sc.nextLine();
        System.out.print("Ingrese el numero de telefono: ");
        String numeroTelefono = sc.nextLine();
        String cuit = validarCuit();
        
        try (Connection con = DriverManager.getConnection(url, username, pass)) {
            Statement stmt = con.createStatement();
           
            String query = "INSERT INTO OBRASOCIAL VALUES (" + getNewId(url, username, pass, "obrasocial", "IdObraSocial") + ","
                    + "'" + razonSocial + "', '" + domicilio + "', '" + numeroTelefono + "', '" + cuit + "')";           
           
            if (stmt.executeUpdate(query) == 1) {
                System.out.println("La Obra Social fue añadida al sistema con exito. Presione Enter para continuar.");
                
            } else {
                System.out.println("Fallo al insertar Obra Social!");
            }

        } catch (SQLException e) {
            System.out.println("Código de Error: " + e.getErrorCode() + "\n" +
              "SLQState: " + e.getSQLState() + "\n" +
              "Mensaje: " + e.getMessage() + "\n");            
            System.out.println("Presione Enter para continuar.");
        }        
    }

    public static void modificaObraSocial(String url, String username, String pass, Scanner sc){

        borrarPantalla();
        //Scanner scan = new Scanner(System.in);
        System.out.println("Modifica Obra Social");
        System.out.println("------------------------------------\n");
        System.out.print("Ingrese el Código de Obra Social: ");
        String codiOS = sc.nextLine();
        int codOS = Integer.parseInt(codiOS);
        ObraSocial obrasoc = buscarObraSocial(url, username, pass, codOS);
        obrasoc.mostrarObraSocial();
        //int idpac = pac.getIdPersona();

        
        if(siNo("Desea modificar datos de la Obra Social")){
            System.out.println("Modificando datos...");
            String razonSocial = validarNombrePropio("la Razón Social");           
            System.out.print("Ingrese el domicilio: ");
            String domicilio = sc.nextLine();
            System.out.print("Ingrese el numero de telefono: ");
            String numeroTelefono = sc.nextLine();
            String cuit = validarCuit();
                    
            try (Connection con = DriverManager.getConnection(url, username, pass)) {
                Statement stmt = con.createStatement();

                String query = "UPDATE OBRASOCIAL SET razonsocial = '" + razonSocial + "', domicilio = '" + domicilio + "',"
                    + " telefono = '"+ numeroTelefono + "', cuit = '" + cuit + "' WHERE IdObraSocial = " + obrasoc.getIdObraSocial();                
       
                if (stmt.executeUpdate(query) == 1) {
                    System.out.println("La Obra Social fue modificada con exito. Presione Enter para continuar.");
                }
            } catch (SQLException e) {
                System.out.println("Código de Error: " + e.getErrorCode() + "\n" +
                  "SLQState: " + e.getSQLState() + "\n" +
                  "Mensaje: " + e.getMessage() + "\n");            
                System.out.println("Presione Enter para continuar.");
            }
            
        }else{
            System.out.println("Presione Enter para continuar.");
        }       
    }    
    
    public static void eliminaObraSocial(String url, String username, String pass, Scanner sc){
        //Paciente pac;
        borrarPantalla();
        System.out.println("Eliminar Obra Social");
        System.out.println("------------------------------------\n");
        int idObraSoc = buscarIdObraSocial(url, username, pass);

        if(siNo("Desea eliminar la Obra Social")){
            try (Connection con = DriverManager.getConnection(url, username, pass)) {
                Statement stmt = con.createStatement();

                String query = "DELETE FROM OBRASOCIAL WHERE IdObraSocial = " + idObraSoc;
                
                if (stmt.executeUpdate(query) == 1) {
                    System.out.println("La Obra Social fue eliminada del sistema con exito. Presione Enter para continuar.");
                }
            } catch (SQLException e) {
                System.out.println("Código de Error: " + e.getErrorCode() + "\n" +
                  "SLQState: " + e.getSQLState() + "\n" +
                  "Mensaje: " + e.getMessage() + "\n");
                System.out.println("\nPresione Enter para continuar.");
            }            
        }else{
            System.out.println("Presione Enter para continuar.");
        }       
    }    
    
    public static void mostrarObraSocial(String url, String username, String pass, Scanner sc){
        String query = "SELECT * FROM obrasocial";

        System.out.println("Lista de Obras Sociales");
        System.out.println(rPad("",200,'-'));
        System.out.println(rPad("Codigo",12,' ')+"\t"+lPad("Razon Social",60,' ')+lPad("Domicilio",50,' ')+lPad("Telefono",20,' ')+lPad("Cuit",15,' '));
        System.out.println(rPad("",200,'-'));

        try (Connection con = DriverManager.getConnection(url, username, pass)) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                
                String cod = rs.getString("idobrasocial");
                String rsocial = rs.getString("razonsocial");
                String domic = rs.getString("domicilio");
                String tel = rs.getString("telefono");
                String cuit = rs.getString("cuit");

                System.out.println(rPad(cod.trim(),12,' ')+"\t"+lPad(rsocial.trim(),60,' ')+lPad(domic.trim(),50,' ')+lPad(tel.trim(),20,' ')+lPad(cuit.trim(),15,' '));
            }
            System.out.println("\nPresione Enter para continuar.");
            
        } catch (SQLException e) {
            System.out.println("Código de Error: " + e.getErrorCode() + "\n" +
              "SLQState: " + e.getSQLState() + "\n" +
              "Mensaje: " + e.getMessage() + "\n");            
            System.out.println("Presione Enter para continuar.");
        }        
    }
    
    public static void agregaTurno(String url, String username, String pass, Scanner sc){
     
        borrarPantalla();
        System.out.println("Agregar Turno");
        System.out.println("------------------------------------\n");
        int pac = buscarIdPaciente(url, username, pass);
        int med = buscarIdMedico(url, username, pass);
        int posi;
        Date fechaTurno = validarFecha("Turno");
        boolean valido = false;
        do {
            posi = validarHoraTurno(url, username, pass);
            DateFormat df = new SimpleDateFormat("HH:mm:ss");
            //System.out.println(">> "+horaTurno);
            String hora = df.format(horaTurno);
            //Date hora = horaTurno.;
            // new java.sql.Time(hora.getTime())
            try (Connection con = DriverManager.getConnection(url, username, pass)) {
                String query = "SELECT posicion, horainicial,apellidoYNombre FROM turnos INNER JOIN paciente ON turnos.idpaciente = paciente.idpaciente"
                +" WHERE idmedico = " + med + " and fecha = '"+new java.sql.Date(fechaTurno.getTime())+"' and horaInicial = '"+ hora + "' order by posicion";
                Statement stmt = con.createStatement();
                
                ResultSet rs = stmt.executeQuery(query);
                boolean tieneReg = rs.last();
                if (tieneReg){
                    System.out.println("Dicho Turno esta ocupado por: "+rs.getString("apellidoYNombre") +", revise los turnos disponibles por favor");
                }else{
                    valido = true;
                }
            } catch (SQLException e) {
                //System.out.println("Excepcion creando la conexión: " + e);
                System.out.println("Excepcion creando la conexión - Código de Error: " + e.getErrorCode() + "\n" +
                  "SLQState: " + e.getSQLState() + "\n" +
                  "Mensaje: " + e.getMessage() + "\n");                
            }
        } while (!valido);

        try (Connection con = DriverManager.getConnection(url, username, pass)) {
            Statement stmt = con.createStatement();
           
            String query = "INSERT INTO TURNOS VALUES (" + getNewId(url, username, pass, "turnos", "IdTurno") + ","
                    + pac + ", " + med + ", '" + new java.sql.Date(fechaTurno.getTime()) + "', '" + new java.sql.Time(horaTurno.getTime()) + "', '00:00', 0, "+posi+")";           
           
            if (stmt.executeUpdate(query) == 1) {
                System.out.println("El turno fue añadido al sistema con exito. Presione Enter para continuar.");                
            } else {
                System.out.println("Fallo al insertar el turno!");
            }
        } catch (SQLException e) {
                System.out.println("Excepcion creando la conexión - Código de Error: " + e.getErrorCode() + "\n" +
                  "SLQState: " + e.getSQLState() + "\n" +
                  "Mensaje: " + e.getMessage() + "\n");
                System.out.println("Presione Enter para continuar.");
        }        
    }

    public static void eliminaTurno(String url, String username, String pass, Scanner sc){
     
        borrarPantalla();
        System.out.println("Eliminar Turno");
        System.out.println("------------------------------------\n");
        int med = buscarIdMedico(url, username, pass);
        int posi;
        int idturno = 0;
        String datosTurno = "";
        Date fechaTurno = validarFecha("Turno");
        boolean valido = false;
        do {
            posi = validarHoraTurno(url, username, pass);
            DateFormat df = new SimpleDateFormat("HH:mm:ss");
            //System.out.println(">> "+horaTurno);
            String hora = df.format(horaTurno);
            //Date hora = horaTurno.;
            // new java.sql.Time(hora.getTime())
            try (Connection con = DriverManager.getConnection(url, username, pass)) {
                String query = "SELECT posicion, horainicial,apellidoYNombre,idturno FROM turnos INNER JOIN paciente ON turnos.idpaciente = paciente.idpaciente"
                +" WHERE idmedico = " + med + " and fecha = '"+new java.sql.Date(fechaTurno.getTime())+"' and horaInicial = '"+ hora + "' order by posicion";
                Statement stmt = con.createStatement();
                
                ResultSet rs = stmt.executeQuery(query);
                boolean tieneReg = rs.last();
                if (!tieneReg){
                    System.out.println("Dicho Turno No Existe ");
                }else{
                    datosTurno = rs.getString("apellidoYNombre")+" "+rs.getString("horainicial");
                    idturno = rs.getInt("idturno");
                    valido = true;
                }
            } catch (SQLException e) {
                System.out.println("Excepcion creando la conexión - Código de Error: " + e.getErrorCode() + "\n" +
                  "SLQState: " + e.getSQLState() + "\n" +
                  "Mensaje: " + e.getMessage() + "\n");
            }
        } while (!valido);
        
        if(siNo(datosTurno)){
            try (Connection con = DriverManager.getConnection(url, username, pass)) {
                Statement stmt = con.createStatement();

                String query = "DELETE FROM TURNOS WHERE IDTURNO = " + idturno;           

                if (stmt.executeUpdate(query) == 1) {
                    System.out.println("El turno fue eliminado con exito. Presione Enter para continuar.");                
                } else {
                    System.out.println("Fallo al borrar el turno!");
                }
            } catch (SQLException e) {
                System.out.println("Excepcion creando la conexión - Código de Error: " + e.getErrorCode() + "\n" +
                  "SLQState: " + e.getSQLState() + "\n" +
                  "Mensaje: " + e.getMessage() + "\n");
                System.out.println("Presione Enter para continuar.");                
            }              
        }      
    }    
    
    public static void mostrarTurnoMedico(String url, String username, String pass, Scanner sc){

        String rojo = "\033[31m";
        String verde = "\033[32m";
        String azul = "\033[34m";
        String morado = "\033[35m";
        
        borrarPantalla();
        String [] turnos = {"08:00","08:20","08:40","09:00","09:20","09:40","10:00","10:20","10:40","11:00","11:20","11:40","12:00"};
        String [] pacien = new String [13];
        int medic = buscarIdMedico(url, username, pass);
        Date fecha = validarFecha("Turno");
        String query = "SELECT posicion, horainicial,apellidoYNombre FROM turnos INNER JOIN paciente ON turnos.idpaciente = paciente.idpaciente"
                +" WHERE idmedico = " + medic + " and fecha = '"+new java.sql.Date(fecha.getTime())+"' order by posicion";
        
        System.out.println("\n\nLista de Turnos\t"+azul+"[Disponible]"+verde+"  --------  "+rojo+"[Ocupado]");
        System.out.println(rPad("",80,'-'));
        System.out.println(rPad("Hora",12,' ')+"\t"+lPad("Paciente",80,' '));
        System.out.println(rPad("",80,'-'));

        try (Connection con = DriverManager.getConnection(url, username, pass)) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {                
                int pos = rs.getInt("posicion");
                String cod = rs.getString("HoraInicial");
                String paci = rs.getString("apellidoYNombre");
                turnos[pos] = cod;
                pacien[pos] = paci.trim();
            }
            for (int posicion = 0; posicion < 13; posicion++) {
                if (turnos[posicion].length()>5){
                    System.out.println(rojo+rPad(turnos[posicion],12,' ')+"\t"+lPad(pacien[posicion],80,' '));
                }else{
                    System.out.println(azul+rPad(turnos[posicion],12,' ')+"\t"+lPad(pacien[posicion],80,' '));
                }
                
            }   
            System.out.println("\nPresione Enter para continuar.");
            
        } catch (SQLException e) {
            System.out.println("Código de Error: " + e.getErrorCode() + "\n" +
              "SLQState: " + e.getSQLState() + "\n" +
              "Mensaje: " + e.getMessage() + "\n");            
            System.out.println("Presione Enter para continuar.");
        }
    }    
    
    public static void mostrarTurnoFecha(String url, String username, String pass, Scanner sc){
        
        borrarPantalla();
        Date fecha = validarFecha("Turno");
        String query = "SELECT posicion, horainicial, paciente.apellidoYNombre as apenom1, medico.apellidoYNombre as apenom2 FROM turnos"
                +" INNER JOIN paciente ON turnos.idpaciente = paciente.idpaciente" 
                +" INNER JOIN medico ON turnos.idmedico = medico.idmedico where fecha = '"+new java.sql.Date(fecha.getTime())+"' order by 4,1";
        
        System.out.println("Lista de Turnos");
        System.out.println(rPad("",112,'-'));
        System.out.println(lPad("Medico",50,' ')+rPad("Hora",12,' ')+"\t"+lPad("Paciente",50,' '));
        System.out.println(rPad("",112,'-'));

        try (Connection con = DriverManager.getConnection(url, username, pass)) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {                
                String cod = rs.getString("HoraInicial");
                String paci = rs.getString("apenom1");
                String medi = rs.getString("apenom2");
                System.out.println(lPad(medi.trim(),50,' ')+rPad(cod.trim(),12,' ')+"\t"+lPad(paci.trim(),50,' '));
            }
            
            System.out.println("\nPresione Enter para continuar.");
            
        } catch (SQLException e) {
            System.out.println("Código de Error: " + e.getErrorCode() + "\n" +
              "SLQState: " + e.getSQLState() + "\n" +
              "Mensaje: " + e.getMessage() + "\n");            
            System.out.println("Presione Enter para continuar.");
        }
    }
    
    public static Date validarFecha(String mensaje){
        Date testDate = null;
        boolean valido = false;
        do 
        {
           System.out.print("Ingrese la fecha " + mensaje + " con formato dd/mm/yyyy :");
           Scanner sc = new Scanner(System.in);
           String fecha = sc.nextLine();
           SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           
           String date = fecha;
           try{
               testDate = df.parse(date);
               //System.out.println("Ahora hemos creado un objeto date con la fecha indicada, "+testDate);
           } catch (Exception e){ 
               System.out.println("Formato no valido");
           }
            if (!df.format(testDate).equals(date)){
                System.out.println("Fecha no valida!!");
            } else {
                //System.out.println("valid date");
                valido = true;
            }           
        } while (!valido);
            //System.out.println("invalid date!!");
        return testDate;
    }

    public static Date validarHora(String mensaje){
        Date testDate = null;
        boolean valido = false;
        String date = "";
        do 
        {
           System.out.print("Ingrese la hora " + mensaje + " con formato hh:mm :");
           Scanner sc = new Scanner(System.in);
           date = sc.nextLine();
           DateFormat df = new SimpleDateFormat("HH:mm");
           try{
               testDate = df.parse(date);
               //String otrafec = df.format(testDate);
               //System.out.println("Ahora hemos creado un objeto date con la fecha indicada, "+testDate);
           } catch (Exception e){ 
               System.out.println("Formato no valido");
           }
            if (!df.format(testDate).equals(date)){
                System.out.println("Hora no valida!!");
            } else {
                //System.out.println("valid date");
                valido = true;
            }           
        } while (!valido);
            //System.out.println("invalid date!!");
        return testDate;
    }    

    public static int validarHoraTurno(String url, String username, String pass){
        boolean valido = false;
        int posi = -1;
        String [] turnos = {"08:00","08:20","08:40","09:00","09:20","09:40","10:00","10:20","10:40","11:00","11:20","11:40","12:00"};
        
        try {
            do {            
                horaTurno = validarHora("Turno");
                DateFormat df = new SimpleDateFormat("HH:mm");
                String hora = df.format(horaTurno).trim();
                posi = Arrays.binarySearch(turnos, hora);
                if (posi < 0) {
                    System.out.println("Solo puede ingresar como hora la siguiente lista: 08:00, 08:20, 08:40, 09:00, 09:20, 09:40, 10:00, 10:20, 10:40, 11:00, 11:20, 11:40, 12:00");               
                }else{
                    valido = true;
                }           
            } while (!valido);            
        } catch (Exception e) {
                System.out.println("Código de Error: \n" + "Mensaje: " + e.getMessage() + "\n");            
        }
        
        return posi;
    }
       
    public static int buscarIdObraSocial(String url, String username, String pass){
        int os = 0;
        boolean valido = false;
        do 
        {
            System.out.print("Ingrese el codigo de Obra Social: ");
            Scanner sc = new Scanner(System.in);
            os = sc.nextInt();
            try (Connection con = DriverManager.getConnection(url, username, pass)) {
                Statement stmt = con.createStatement();
                String query = "SELECT * FROM OBRASOCIAL WHERE IdObraSocial = " + os;
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String razonsoc = rs.getString("razonsocial");
                    System.out.println("Obra Social seleccionada:"+razonsoc);
                    valido = true;
                }
                        
                if (!valido) {    
                    System.out.println("No existe la obrasocial ingresada!");
                }
                
            } catch (SQLException e) {
                System.out.println("Código de Error: " + e.getErrorCode() + "\n" +
                  "SLQState: " + e.getSQLState() + "\n" +
                  "Mensaje: " + e.getMessage() + "\n");            
                System.out.println("Presione Enter para continuar.");
            }
        } while (!valido);
            
        return os;
    }

    public static ObraSocial buscarObraSocial(String url, String username, String pass, int os){
        ObraSocial obrasoc = new ObraSocial();
        boolean valido = false;
        try (Connection con = DriverManager.getConnection(url, username, pass)) {
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM OBRASOCIAL WHERE IdObraSocial = " + os;
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                obrasoc.setIdObraSocial(os);
                obrasoc.setNombreObraSocial(rs.getString("razonsocial"));
                obrasoc.setDomicilio(rs.getString("domicilio"));
                obrasoc.setTelefono(rs.getString("telefono"));
                obrasoc.setCuit(rs.getString("cuit"));
                System.out.println("Obra Social seleccionada:"+obrasoc.getNombreObraSocial());
                valido = true;
            }

            if (!valido) {    
                System.out.println("No existe la obrasocial ingresada!");
            }

        } catch (SQLException e) {
            System.out.println("Código de Error: " + e.getErrorCode() + "\n" +
              "SLQState: " + e.getSQLState() + "\n" +
              "Mensaje: " + e.getMessage() + "\n");            
            System.out.println("Presione Enter para continuar.");
        }            
        return obrasoc;
    }
    
    public static int buscarIdPaciente(String url, String username, String pass){
        int nPac = 0;
        int idPac = 0;
        boolean valido = false;
 
        do 
        {
            System.out.print("Ingrese el numero de documento del paciente: ");
            Scanner sc = new Scanner(System.in);
            nPac = sc.nextInt();

            try (Connection con = DriverManager.getConnection(url, username, pass)) {
                Statement stmt = con.createStatement();
                
                String query = "SELECT * FROM PACIENTE WHERE NUMERODOCUMENTO = " + nPac;
                
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String nomPaciente = rs.getString("apellidoYNombre");                   
                    System.out.println("Paciente :"+nomPaciente);
                    idPac = rs.getInt("IdPaciente");
                    valido = true;
                }
                        
                if (!valido) {    
                    System.out.println("No existe el paciente ingresado!");
                }
                
            } catch (SQLException e) {
                System.out.println("Código de Error: " + e.getErrorCode() + "\n" +
                  "SLQState: " + e.getSQLState() + "\n" +
                  "Mensaje: " + e.getMessage() + "\n");            
                System.out.println("Presione Enter para continuar.");
            }
        } while (!valido);
            
        return idPac;
    }    

    public static Paciente buscarPaciente(String url, String username, String pass){
        Paciente pac = new Paciente();
        int nPac = 0;
        
        boolean valido = false;
 
        do 
        {
            System.out.print("Ingrese el numero de documento del paciente: ");
            Scanner sc = new Scanner(System.in);
            nPac = sc.nextInt();

            try (Connection con = DriverManager.getConnection(url, username, pass)) {
                Statement stmt = con.createStatement();
                
                String query = "SELECT * FROM PACIENTE INNER JOIN OBRASOCIAL ON paciente.idobrasocial = obrasocial.idobrasocial WHERE NUMERODOCUMENTO = " + nPac;
                
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    pac.setIdPersona(rs.getInt("idpaciente"));
                    pac.setApellidoYNombre(rs.getString("apellidoYNombre"));
                    pac.setDomicilio(rs.getString("domicilio"));
                    pac.setTipoDocumento(rs.getString("tipodocumento"));
                    pac.setNumeroDocumento(rs.getInt("numerodocumento"));
                    pac.setFechaNacimiento(rs.getDate("fechanacimiento"));
                    pac.setTelefono(rs.getString("telefono"));
                    pac.setNacionalidad(rs.getString("nacionalidad"));
                    pac.setSexo(rs.getString("sexo"));
                    pac.setEmail(rs.getString("email"));
                    pac.setCodigoAfiliadoObraSocial(rs.getString("numeroafiliadoObraSocial"));
                    pac.setPlanObraSocial(rs.getString("nombrePlanObraSocial"));
                    pac.setObraSocial(buscarObraSocial(url, username, pass, rs.getInt("idObraSocial")));
                    valido = true;
                }
                        
                if (!valido) {    
                    System.out.println("No existe el paciente ingresado!");
                }
                
            } catch (SQLException e) {
                System.out.println("Código de Error: " + e.getErrorCode() + "\n" +
                  "SLQState: " + e.getSQLState() + "\n" +
                  "Mensaje: " + e.getMessage() + "\n");            
                System.out.println("Presione Enter para continuar.");
            }
        } while (!valido);
            
        return pac;
    }    
    
    public static int buscarIdMedico(String url, String username, String pass){
        int nMed = 0;
        int idMed = 0;
        boolean valido = false;
 
        do 
        {
            System.out.print("Ingrese el código de médico: ");
            Scanner sc = new Scanner(System.in);
            nMed = sc.nextInt();

            try (Connection con = DriverManager.getConnection(url, username, pass)) {
                Statement stmt = con.createStatement();
                
                String query = "SELECT * FROM MEDICO WHERE IDMEDICO = " + nMed;
                
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String nomPaciente = rs.getString("apellidoYNombre");                   
                    System.out.println("Médico :"+nomPaciente);
                    idMed = rs.getInt("IdMedico");
                    valido = true;
                }
                        
                if (!valido) {    
                    System.out.println("No existe el médico ingresado!");
                }
                
            } catch (SQLException e) {
                System.out.println("Código de Error: " + e.getErrorCode() + "\n" +
                  "SLQState: " + e.getSQLState() + "\n" +
                  "Mensaje: " + e.getMessage() + "\n");            
                System.out.println("Presione Enter para continuar.");
            }
        } while (!valido);
            
        return idMed;
    }    

    public static Medico buscarMedico(String url, String username, String pass){
        Medico med = new Medico();
        
        int nMed = 0;
        
        boolean valido = false;
 
        do 
        {
            System.out.print("Ingrese el código de médico: ");
            Scanner sc = new Scanner(System.in);
            nMed = sc.nextInt();

            try (Connection con = DriverManager.getConnection(url, username, pass)) {
                Statement stmt = con.createStatement();
                
                String query = "SELECT * FROM MEDICO WHERE IDMEDICO = " + nMed;
                
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    med.setIdPersona(rs.getInt("idmedico"));
                    med.setApellidoYNombre(rs.getString("apellidoYNombre"));
                    med.setDomicilio(rs.getString("domicilio"));
                    med.setTipoDocumento(rs.getString("tipodocumento"));
                    med.setNumeroDocumento(rs.getInt("numerodocumento"));
                    med.setFechaNacimiento(rs.getDate("fechanacimiento"));
                    med.setTelefono(rs.getString("telefono"));
                    med.setNacionalidad(rs.getString("nacionalidad"));
                    med.setSexo(rs.getString("sexo"));
                    med.setEmail(rs.getString("email"));
                    med.setProfesion(rs.getString("profesion"));
                    med.setCuit(rs.getString("cuit"));
                    med.setNumeroMatricula(rs.getInt("numeromatricula"));
                    med.setCategoriaIva(rs.getString("categoriaiva"));
                    valido = true;
                }
                        
                if (!valido) {    
                    System.out.println("No existe el paciente ingresado!");
                }
                
            } catch (SQLException e) {
                System.out.println("Código de Error: " + e.getErrorCode() + "\n" +
                  "SLQState: " + e.getSQLState() + "\n" +
                  "Mensaje: " + e.getMessage() + "\n");            
                System.out.println("Presione Enter para continuar.");
            }
        } while (!valido);
            
        return med;
    }
    
    public static int getNewId(String url, String username, String pass, String tabla, String campoId) {
        int IdNew = 0;
        try (Connection con = DriverManager.getConnection(url, username, pass)) {
            Statement stmt = con.createStatement();
            String query = "SELECT MAX("+ campoId + ") as Maximo FROM "+ tabla;
            ResultSet rs = stmt.executeQuery(query);        
            while (rs.next()) {
                IdNew = rs.getInt("Maximo")+1;
            }
        }catch (SQLException e) {
            System.out.println("Código de Error: " + e.getErrorCode() + "\n" +
              "SLQState: " + e.getSQLState() + "\n" +
              "Mensaje: " + e.getMessage() + "\n");            
            System.out.println("Presione Enter para continuar.");
        }
        return IdNew;
    }

    public static String validarNombrePropio(String mensaje) {
        boolean valido = false;
        String nomVerificado = "";
        do {
            System.out.print("Ingrese "+ mensaje +": ");
            Scanner sc = new Scanner(System.in);
            nomVerificado = sc.nextLine();            
            if (nomVerificado.matches("([A-Z]{1}[a-z]+[ ]?){2}")) {
                valido = true;
            } else {
                System.out.println("Ingreso un nombre no valido");
            }            
        } while (!valido);
        
        return nomVerificado;
    }    

    public static String validarDocumento(String mensaje) {
        boolean valido = false;
        String numeroDoc = "";
        do {
            System.out.print("Ingrese "+ mensaje +": ");
            Scanner sc = new Scanner(System.in);
            numeroDoc = sc.nextLine();

            int largo = numeroDoc.length();
            String numDocRegexp;
            if (largo ==7 | largo == 8){
                if (largo ==7 ){
                    numDocRegexp = "\\d{7}";
                }else{
                    numDocRegexp = "\\d{8}";
                }
                if(Pattern.matches(numDocRegexp, numeroDoc)==true){
                    valido = true;
                }else{
                    System.out.println("El número de documento debe tener entre 7 y 8 digitos!");}
            }else {
                System.out.println("El número de documento debe tener entre 7 y 8 digitos!");
            }         
        } while (!valido);
        
        return numeroDoc;
    }
    
    public static String validarTipoDoc(){
        boolean valido = false;
        String tipoDoc = "";
        do {
            System.out.print("Ingrese tipo de documento [DNI]/[CI]/[PAS]: ");
            Scanner sc = new Scanner(System.in);
            tipoDoc = sc.nextLine();
            String numDocRegexp;
            
            switch(tipoDoc.toUpperCase().trim()){
                case "DNI": valido=true;break;
                case "CI" : valido=true;break;
                case "PAS": valido=true;break;
                default: System.out.println("Valor no valido!!");
            }       
        } while (!valido);
        
        return tipoDoc;
    }
    
    public static String validarSexo() {
        boolean valido = false;
        String sexo = "";
        do {
            System.out.print("Ingrese el sexo [F]/[M]: ");
            Scanner sc = new Scanner(System.in);
            sexo = sc.nextLine();
            switch (sexo){
                case "f": valido=true;break;
                case "F": valido=true;break;
                case "M": valido=true;break;
                case "m": valido=true;break;
                default: System.out.println("Valor no valido!!"); 
            }
        } while (!valido);
        
        return sexo;
    }
    
    public static String validarCuit(){
        boolean valido = false;
        String cuitVerificado = "";
        do {
            System.out.print("Ingrese CUIT [##-########-#] : ");
            Scanner sc = new Scanner(System.in);
            cuitVerificado = sc.nextLine();            
            if (cuitVerificado.matches("\\b(20|23|24|27|30|33|34)\\-[0-9]{8}\\-[0-9]")){
                if (cuitDigitoVerificador(cuitVerificado)) {
                    valido = true;
                } else {
                    System.out.println("Ingreso un CUIT no valido ");
                }
            }else {
                System.out.println("Ingreso un CUIT no acorde al formato");
            }            
        } while (!valido);
        
        return cuitVerificado;
    }
    
    public static boolean cuitDigitoVerificador(String cuit){
        boolean retorno = true;
        String [] partes = cuit.split("-");
        String cCuit = partes[0]+partes[1]+partes[2];
        int suma = 0;
        suma = suma + Integer.parseInt(cCuit.substring(0, 1)) * 5;
        suma = suma + Integer.parseInt(cCuit.substring(1, 2)) * 4;
        suma = suma + Integer.parseInt(cCuit.substring(2, 3)) * 3;
        suma = suma + Integer.parseInt(cCuit.substring(3, 4)) * 2;
        suma = suma + Integer.parseInt(cCuit.substring(4, 5)) * 7;
        suma = suma + Integer.parseInt(cCuit.substring(5, 6)) * 6;
        suma = suma + Integer.parseInt(cCuit.substring(6, 7)) * 5;
        suma = suma + Integer.parseInt(cCuit.substring(7, 8)) * 4;
        suma = suma + Integer.parseInt(cCuit.substring(8, 9)) * 3;
        suma = suma + Integer.parseInt(cCuit.substring(9, 10)) * 2;
        int digito = 11 - (suma%11);
        if (digito == 11){
            digito = 0;
        }
        if (digito != Integer.parseInt(cCuit.substring(10, 11))){
            retorno = false;
        }
        return retorno;
    }    

    public static boolean siNo(String mensaje){
        boolean valido = false;
        String opcion = "";
        boolean respuesta = false;
        do {
            System.out.print(mensaje +" [S]/[N] ?: ");
            Scanner sc = new Scanner(System.in);
            opcion = sc.nextLine();

            switch (opcion){
                case "s":
                case "S":
                    respuesta = true;
                    valido = true;
                    break;
                case "n":
                case "N":
                    valido = true;
                    break;
                default :
                    System.out.println("debe ingresar un valor correcto");
            }           
        } while (!valido);
        
        return respuesta;
    }

    public static String obtenerFechaFormateada(Date fecha, String formato) {
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        return sdf.format(fecha);
    } 
}

