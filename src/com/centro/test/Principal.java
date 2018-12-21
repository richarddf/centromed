/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.centro.test;

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
        String pass = "";
        //String pass = "";
        
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
                    //agregaTurno(url, username, pass, sc);
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
        for (int clear = 0; clear < 1000; clear++) {
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
        //System.out.print("Ingrese el Apellido y Nombre del paciente: ");
        String apellidoYNombre = validarNombrePropio("el Apellido y Nombre del paciente");//sc.nextLine();
        System.out.print("Ingrese el domicilio: ");
        String domicilio = sc.nextLine();
        System.out.print("Ingrese tipo de documento [DNI]/[CI]/[PAS]: ");
        String tipoDocumento = sc.nextLine();
        //System.out.print("Ingrese el numero de documento del paciente: ");
        String numeroDocumento = validarDocumento("el numero de documento del paciente");
        Date fechaNac = validarFecha("Nacimiento");
        System.out.print("Ingrese la nacionalidad ");
        String nacionalidad = sc.nextLine();
        System.out.print("Ingrese el numero de telefono: ");
        String numeroTelefono = sc.nextLine();
        //System.out.print("Ingrese el sexo [F]/[M]: ");
        String sexo = validarSexo();//sc.nextLine();
        System.out.print("Ingrese el email: ");
        String email = sc.nextLine();
        int obraSoc = validarOS(url, username, pass);
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
            System.out.println("Excepcion creando la conexión: " + e);
            System.exit(0);
        }        
    }

    public static void modificaPaciente(String url, String username, String pass, Scanner sc){
        //Paciente pac;
        borrarPantalla();
        System.out.println("Modificar Paciente");
        System.out.println("------------------------------------\n");
        int idpac = validarPaciente(url, username, pass);
        String query = "SELECT * FROM PACIENTE WHERE NUMERODOCUMENTO = " + idpac;
        Paciente pac = new Paciente();
        
        if(siNo("Desea eliminar el paciente y su historia clinica")){
            try (Connection con = DriverManager.getConnection(url, username, pass)) {
                Statement stmt = con.createStatement();

                //String query = "DELETE FROM PACIENTE WHERE IdPaciente = " + idpac;
 
                if (stmt.executeUpdate(query) == 1) {
                    System.out.println("El paciente fue eliminado del sistema con exito. Presione Enter para continuar.");
                }
            } catch (SQLException e) {
                System.out.println("Excepcion creando la conexión: " + e);
                System.exit(0);
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
        int idpac = validarPaciente(url, username, pass);

        if(siNo("Desea eliminar el paciente y su historia clinica")){
            try (Connection con = DriverManager.getConnection(url, username, pass)) {
                Statement stmt = con.createStatement();

                String query = "DELETE FROM PACIENTE WHERE IdPaciente = " + idpac;
                String query2 = "DELETE FROM HISTORIACLINICAPACIENTE WHERE IdPaciente = " + idpac;

                if (stmt.executeUpdate(query2) == 1) {
                    System.out.println("La historia clinica del paciente fue eliminada del sistema con exito.");
                }
                
                if (stmt.executeUpdate(query) == 1) {
                    System.out.println("El paciente fue eliminado del sistema con exito. Presione Enter para continuar.");
                }
            } catch (SQLException e) {
                System.out.println("Excepcion creando la conexión: " + e);
                System.exit(0);
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
            System.out.println("Excepcion creando la conexión: " + e);
            System.exit(0);
        }        
    }
    
    public static void mostrarPacientes(String url, String username, String pass, Scanner sc){
        String query = "SELECT apellidoYNombre,paciente.Domicilio,numeroDocumento,fechanacimiento,paciente.telefono,razonsocial FROM paciente"
                +" INNER JOIN obrasocial on paciente.idobrasocial = obrasocial.idobrasocial";

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
                String fechanac = rs.getString("fechanacimiento");
                String tel = rs.getString("telefono");
                String rsocial = rs.getString("razonsocial");

                System.out.println(lPad(apenom.trim(),40,' ')+lPad(domic.trim(),50,' ')+rPad(doc.trim(),10,' ')+rPad(fechanac.trim(),20,' ')+"\t"+lPad(tel.trim(),20,' ')+lPad(rsocial.trim(),30,' '));
            }
            System.out.println("\nPresione Enter para continuar.");

        } catch (SQLException e) {
            System.out.println("Exception creating connection: " + e);
            System.exit(0);
        }        
    }

    public static void mostrarHistoriaClinica(String url, String username, String pass){
        borrarPantalla();
        System.out.println("Historia Clinica");
        System.out.println("------------------------------------\n");
        int idpac = validarPaciente(url, username, pass);

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
            System.out.println("Exception creating connection: " + e);
            System.exit(0);
        }       
    }
    
    public static void agregaMedico(String url, String username, String pass, Scanner sc){
        
        borrarPantalla();
        System.out.println("Agregar Médico");
        System.out.println("------------------------------------\n");
        //System.out.print("Ingrese el Apellido y Nombre del Médico: ");
        String apellidoYNombre = validarNombrePropio("el Apellido y Nombre del Médico");//sc.nextLine();
        System.out.print("Ingrese el domicilio: ");
        String domicilio = sc.nextLine();
        System.out.print("Ingrese tipo de documento [DNI]/[CI]/[PAS]: ");
        String tipoDocumento = sc.nextLine();
        //System.out.print("Ingrese el numero de documento del paciente: ");
        //String numeroDocumento = sc.nextLine();
        String numeroDocumento = validarDocumento("el numero de documento del médico");
        Date fechaNac = validarFecha("Nacimiento");
        System.out.print("Ingrese la nacionalidad ");
        String nacionalidad = sc.nextLine();
        System.out.print("Ingrese el numero de telefono: ");
        String numeroTelefono = sc.nextLine();
        //System.out.print("Ingrese el sexo [F]/[M]: ");
        String sexo = validarSexo();//sc.nextLine();
        System.out.print("Ingrese el email: ");
        String email = sc.nextLine();
        System.out.print("Ingrese la Profesión: ");
        String profesion = sc.nextLine();        
        //System.out.print("Ingrese CUIT: ");
        //String cuit = sc.nextLine();
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
            System.out.println("Excepcion creando la conexión: " + e);
            System.exit(0);
        }        
    }

    public static void eliminaMedico(String url, String username, String pass, Scanner sc){
        //Paciente pac;
        borrarPantalla();
        System.out.println("Eliminar Médico");
        System.out.println("------------------------------------\n");
        int idMed = validarMedico(url, username, pass);

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
                //System.out.println("Excepcion creando la conexión: " + e);
                //System.exit(0);
            }            
        }else{
            System.out.println("Presione Enter para continuar.");
        }       
    }    
    
    public static void mostrarMedicos(String url, String username, String pass, Scanner sc){
        String query = "SELECT apellidoYNombre,Domicilio,numeroDocumento,fechanacimiento,telefono,profesion,cuit FROM medico";

        System.out.println("Lista de Médicos");
        System.out.println(rPad("",200,'-'));
        System.out.println(lPad("Apellido Y Nombre",40,' ')+lPad("Domicilio",50,' ')+rPad("Documento",10,' ')+rPad("Fecha Nac",20,' ')+"\t"+lPad("Telefono",20,' ')+lPad("Profesión",30,' ')+lPad("Cuit",15,' '));
        System.out.println(rPad("",200,'-'));

        try (Connection con = DriverManager.getConnection(url, username, pass)) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                
                String apenom = rs.getString("apellidoYNombre");
                String domic = rs.getString("domicilio");
                String doc = rs.getString("numeroDocumento");
                String fechanac = rs.getString("fechanacimiento");
                String tel = rs.getString("telefono");
                String profe = rs.getString("profesion");
                String cuit = rs.getString("cuit");

                System.out.println(lPad(apenom.trim(),40,' ')+lPad(domic.trim(),50,' ')+rPad(doc.trim(),10,' ')+rPad(fechanac.trim(),20,' ')+"\t"+lPad(tel.trim(),20,' ')+lPad(profe.trim(),30,' ')+lPad(cuit.trim(),15,' '));
            }
            System.out.println("\nPresione Enter para continuar.");
            
        } catch (SQLException e) {
            System.out.println("Código de Error: " + e.getErrorCode() + "\n" +
              "SLQState: " + e.getSQLState() + "\n" +
              "Mensaje: " + e.getMessage() + "\n");
            System.out.println("\nPresione Enter para continuar.");
            //System.out.println("Exception creating connection: " + e);
            //System.exit(0);
        }        
    }

    public static void agregaObraSocial(String url, String username, String pass, Scanner sc){
        
        borrarPantalla();
        System.out.println("Agregar Obra Social");
        System.out.println("------------------------------------\n");
        //System.out.print("Ingrese la Razón Social: ");
        String razonSocial = validarNombrePropio("la Razón Social");//sc.nextLine();
        System.out.print("Ingrese el domicilio: ");
        String domicilio = sc.nextLine();
        System.out.print("Ingrese el numero de telefono: ");
        String numeroTelefono = sc.nextLine();
        //System.out.print("Ingrese CUIT: ");
        String cuit = validarCuit();
        // cuit = validaCuit(cuit);      
        
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
            System.out.println("Excepcion creando la conexión: " + e);
            System.exit(0);
        }        
    }

    public static void eliminaObraSocial(String url, String username, String pass, Scanner sc){
        //Paciente pac;
        borrarPantalla();
        System.out.println("Eliminar Obra Social");
        System.out.println("------------------------------------\n");
        int idObraSoc = validarOS(url, username, pass);

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
                //System.out.println("Excepcion creando la conexión: " + e);                
                //System.exit(0);
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
            System.out.println("Exception creating connection: " + e);
            System.exit(0);
        }        
    }
    
    public static void agregaTurno(String url, String username, String pass, Scanner sc){
        //String [] turnos = {"08:00","08:20","08:40","09:00","09:20","09:40","10:00","10:20","10:40","11:00","11:20","11:40","12:00"};
        borrarPantalla();
        System.out.println("Agregar Turno");
        System.out.println("------------------------------------\n");
        int pac = validarPaciente(url, username, pass);
        int med = validarMedico(url, username, pass);
        Date fechaTurno = validarFecha("Turno");
        int posi = validarHoraTurno(url, username, pass);
        
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
            System.out.println("Excepcion creando la conexión: " + e);
            System.exit(0);
        }        
    }

    
    public static void mostrarTurnoMedico(String url, String username, String pass, Scanner sc){

        String rojo = "\033[31m";
        String verde = "\033[32m";
//        String naranja = "\033[33m";
        String azul = "\033[34m";
//        String morado = "\033[35m";

        
        borrarPantalla();
        String [] turnos = {"08:00","08:20","08:40","09:00","09:20","09:40","10:00","10:20","10:40","11:00","11:20","11:40","12:00"};
        String [] pacien = new String [13];
        int medic = validarMedico(url, username, pass);
        Date fecha = validarFecha("Turno");
        String query = "SELECT posicion, horainicial,apellidoYNombre FROM turnos INNER JOIN paciente ON turnos.idpaciente = paciente.idpaciente where idmedico = " + medic + " and fecha = '"+new java.sql.Date(fecha.getTime())+"' order by posicion";
        
        System.out.println("Lista de Turnos");
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
            System.out.println("Excepcion creando conexión: " + e);
            System.exit(0);
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
            System.out.println("Excepcion creando conexión: " + e);
            System.exit(0);
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
        int posi;
        String [] turnos = {"08:00","08:20","08:40","09:00","09:20","09:40","10:00","10:20","10:40","11:00","11:20","11:40","12:00"};
        
        do {            
            horaTurno = validarHora("Turno");
            DateFormat df = new SimpleDateFormat("HH:mm");
            String hora = df.format(horaTurno).trim();
            posi = Arrays.binarySearch(turnos, hora);
            if (posi <= 0) {
                System.out.println("Solo puede ingresar como hora la siguiente lista: 08:00, 08:20, 08:40, 09:00, 09:20, 09:40, 10:00, 10:20, 10:40, 11:00, 11:20, 11:40, 12:00");               
            }else{
                valido = true;
            }           
        } while (!valido);
        
        return posi;
    }
       
    public static int validarOS(String url, String username, String pass){
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
                System.out.println("Exception creating connection: " + e);
                System.exit(0);
            }
        } while (!valido);
            
        return os;
    }
 
    public static int validarPaciente(String url, String username, String pass){
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
                System.out.println("Exception creating connection: " + e);
                System.exit(0);
            }
        } while (!valido);
            
        return idPac;
    }    

    public static int validarMedico(String url, String username, String pass){
        int nMed = 0;
        int idMed = 0;
        boolean valido = false;
 
        do 
        {
            System.out.print("Ingrese el numero de médico: ");
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
                System.out.println("Exception creating connection: " + e);
                System.exit(0);
            }
        } while (!valido);
            
        return idMed;
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
            System.out.println("Excepcion creando la conexion: " + e);
            System.exit(0);
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
                    System.out.println("El número de documento es incorrecto!");}
            }else {
                System.out.println("El número de documento es incorrecto!");
            }         
        } while (!valido);
        
        return numeroDoc;
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
}

