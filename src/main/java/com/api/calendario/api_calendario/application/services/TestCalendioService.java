package com.api.calendario.api_calendario.application.services;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import com.api.calendario.api_calendario.core.services.IFestivoService;
import com.api.calendario.api_calendario.domain.entities.Festivo;
import com.api.calendario.api_calendario.domain.entities.Tipo;

@Service
public class TestCalendioService {
   
    
    public IFestivoService festivoService;

    public TestCalendioService(IFestivoService festivoService){
        this.festivoService = festivoService;
    }
    public int Validaraño( int año){
        return año;
    }

public Map<String, LocalDate> domingoRamosYPascua(int año) {

    int a = año % 19, b = año % 4, c = año % 7;
    int d= (19 * a + 24) % 30;
    int e = (2 * b + 4 * c + 6 * d + 5) % 7;
    int diasdespues = d + e;
    LocalDate domingoPascua = LocalDate.of(año, 3, 22).plusDays(diasdespues);
    LocalDate domingoRamos = domingoPascua.minusDays(7);

    return Map.of(
        "Domingo Ramos", domingoRamos,
        "Domingo Pascua", domingoPascua
    );
}
    
    public Boolean Validar(){
        return true;
    }

    
    public List Logica(int año){
        List<Festivo> festivos = festivoService.listar(); 
        Calendar  fechas =  Calendar.getInstance();
        LocalDate fechas2 = LocalDate.of(año, 1, 6);
        fechas.set(Calendar.YEAR, año,Calendar.MONTH, 1,Calendar.DAY_OF_WEEK,6);
        //System.out.println(siguienteLunes(fechas2));
        Map<String,LocalDate> data = domingoRamosYPascua(año);
        LocalDate domingoPascua = data.get("Domingo Pascua");
        List <Map<String, Object>> datos= new ArrayList<>();
        Date Dpascua = Date.from(domingoPascua.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Date calculo = agregarDias(Dpascua,40);
        // Date fecha =    SiguienteLunes(calculo);        
        // System.out.println(fecha);

          for (Festivo festivo :festivos){
            Map<String, Object> ListaFestivos = new LinkedHashMap<>();
            
            if(festivo.getTipo().getId() == 1)
            {
                Date fecha = new Date(año-1900,festivo.getMes()-1,festivo.getDias());
                ListaFestivos.put("nombre", festivo.getNombre());
                ListaFestivos.put("fecha", fecha);
                datos.add(ListaFestivos);
            }
            else if(festivo.getTipo().getId() == 2)
            {
                // Se traslada al siguiente lunes
                Date caculo = new Date(año-1900,festivo.getMes()-1,festivo.getDias());
                Date fecha = siguienteLunes(caculo);
                ListaFestivos.put("nombre", festivo.getNombre());
                ListaFestivos.put("fecha", fecha);
                datos.add(ListaFestivos);
            }
            else if(festivo.getTipo().getId() == 3)
            {
                //Basado en domingo Pasuca
                Date fecha = agregarDias(Dpascua,festivo.getDiaspascua());
                ListaFestivos.put("nombre", festivo.getNombre());
                ListaFestivos.put("fecha", fecha);
                datos.add(ListaFestivos);
            }
            else if(festivo.getTipo().getId() == 4)
            {   
        
                Date calculo1 = agregarDias(Dpascua,festivo.getDiaspascua());
                Date fecha = siguienteLunes(calculo1);
                // Domingo Pascua Y ley puente Festivo
                ListaFestivos.put("nombre", festivo.getNombre());
                ListaFestivos.put("fecha", fecha);
                datos.add(ListaFestivos);
            
            }
        
        }
        
        return datos;
    }
    


    public static Date agregarDias( Date fecha,int dia){

        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        calendario.add(Calendar.DATE ,dia); 

        return calendario.getTime(); 
    }

    public static Date siguienteLunes(Date fecha){

        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        if(calendario.get(Calendar.DAY_OF_WEEK) > Calendar.MONDAY){
            fecha  = agregarDias(fecha, 9-calendario.get(Calendar.DAY_OF_WEEK)); 
        }
        else {
            fecha = agregarDias(fecha, 40);
        }
        return fecha;
    }


    public static String verificar(int año){
        
        /// No se va a necesitar dado que en el Fontend se puede hacer la consula con logica 
        Tipo tipo = new Tipo();
        Festivo festivo = new Festivo();
        
        // 1 solo se modifica el año 
        //String tabla_tipo = tipo.getTipo();
        //         
        // String test = "dia " +festivo.getDia()+ " tipo "+ festivo.getTipo() + "";

        // listar  toda la cada las columnas, utilizar 
        int [] datos =  {año};

        
        // se debe hacer primero la logica que selecciona la tabla tipo que se requiere 

        return "test"; 



    }


}
