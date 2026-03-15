package com.api.calendario.api_calendario.application.services;
import com.api.calendario.api_calendario.domain.entities.Calendario;
import com.api.calendario.api_calendario.domain.entities.Festivo;
import com.api.calendario.api_calendario.domain.entities.Pais;
import com.api.calendario.api_calendario.domain.entities.Tipo;
import com.api.calendario.api_calendario.infrastructure.repository.ICalendarioRepository;
import com.api.calendario.api_calendario.core.services.ICalendarioService;
import com.api.calendario.api_calendario.core.services.IFestivoService;
import com.api.calendario.api_calendario.core.services.IPaisService;
import com.api.calendario.api_calendario.core.services.ITipoService;

import org.springframework.stereotype.Service;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class TestCalendioService {
    private IFestivoService festivoService;
    private ICalendarioService calendarioService;
    private ITipoService tipoService;
    private IPaisService paisService;
    private ICalendarioRepository calendarioRepository;
    public TestCalendioService(IFestivoService festivoService,ICalendarioService calendarioService, ITipoService tipoService, IPaisService paisService, ICalendarioRepository calendarioRepository){
        this.festivoService = festivoService;
        this.calendarioService = calendarioService;
        this.tipoService = tipoService;
        this.paisService = paisService;
        this.calendarioRepository = calendarioRepository;
    }

public  Map<String, LocalDate> domingoRamosYPascua(int año) {
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
    
public  List<Map<String, Object>> logica( int pais, int año) {
    List<Festivo> festivos = festivoService.festivopaises(pais);
    Map<String, LocalDate> data = domingoRamosYPascua(año);
    LocalDate domingoPascua = data.get("Domingo Pascua");
    List<Map<String, Object>> datos = new ArrayList<>();

    for (Festivo festivo : festivos) {
        Map<String, Object> itemFestivo = new LinkedHashMap<>();
        LocalDate fechaCalculada;

        switch (festivo.getTipo().getId()) {
            case 1:
                fechaCalculada = LocalDate.of(año, festivo.getMes(), festivo.getDias());
                break;

            case 2:
                LocalDate fechaFija = LocalDate.of(año, festivo.getMes(), festivo.getDias());
                fechaCalculada = trasladarASiguienteLunes(fechaFija);
                break;

            case 3:
                fechaCalculada = domingoPascua.plusDays(festivo.getDiaspascua());
                break;

            case 4:
                LocalDate fechaBasePascua = domingoPascua.plusDays(festivo.getDiaspascua());
                fechaCalculada = trasladarASiguienteLunes(fechaBasePascua);
                break;

            default:
                continue;
        }

        itemFestivo.put("nombre", festivo.getNombre());
        itemFestivo.put("fecha", fechaCalculada);
        datos.add(itemFestivo);
    }

    return datos;
}
    
public Date agregarDias( Date fecha,int dia){

        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        calendario.add(Calendar.DATE ,dia); 

        return calendario.getTime(); 
    }

public Date siguienteLunes(Date fecha){

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
    public static LocalDate trasladarASiguienteLunes(LocalDate fecha) {
    if (fecha.getDayOfWeek() != DayOfWeek.MONDAY) {
        return fecha.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
    }
    return fecha;
}
public String validar(int pais,int año, int mes, int dia) {
    
    String fechaTexto = String.format("%02d/%02d/%d", dia, mes, año);

    if (fechaValida(fechaTexto)) {
        LocalDate fechaABuscar = LocalDate.of(año, mes, dia);
        List<Map<String, Object>> listaFestivos = logica(pais ,año);    
        return listaFestivos.stream()
            .filter(f -> f.get("fecha").equals(fechaABuscar))
            .map(f -> "Es Festivo")
            .findFirst()
            .orElse("No es festivo");
    }
    return "Fecha no valida";
}


public boolean fechaValida(String fechaStr) {
    DateTimeFormatter formato = DateTimeFormatter
            .ofPattern("dd/MM/uuuu")
            .withResolverStyle(ResolverStyle.STRICT);

    try {
        LocalDate.parse(fechaStr, formato);
        return true;
    } catch (DateTimeParseException e) {
        return false;
    }
}



public List<Calendario> listarCalendario(int paisId, int año) {

    List<Map<String, Object>> festivos = logica(paisId, año);
    Optional<Pais> paisOpt = paisService.obtener(paisId);
    if (paisOpt.isEmpty()) {
        throw new RuntimeException("País no encontrado con id: " + paisId);
    }
    Pais pais = paisOpt.get();

    Tipo tipoFestivo = tipoService.obtener(3);
    Tipo tipoLaboral = tipoService.obtener(1);
    Tipo tipoFinDeSemana = tipoService.obtener(2);
    if (tipoFestivo == null || tipoLaboral == null || tipoFinDeSemana == null) {
        throw new RuntimeException("Tipos de día no encontrados en la base de datos.");
    }

    Set<LocalDate> fechasFestivas = festivos.stream()
                                            .map(festivo -> (LocalDate) festivo.get("fecha"))
                                            .collect(Collectors.toSet());

    List<Calendario> calendarioAGuardar = new ArrayList<>();
    LocalDate fecha = LocalDate.of(año, 1, 1);
    LocalDate finDeAño = LocalDate.of(año, 12, 31);

    while (!fecha.isAfter(finDeAño)) {
        Tipo tipoDia = fechasFestivas.contains(fecha) ? tipoFestivo : tipoLaboral;
        if (fechasFestivas.contains(fecha)) {
            tipoDia = tipoFestivo;
        } else if (fecha.getDayOfWeek() == DayOfWeek.SATURDAY || fecha.getDayOfWeek() == DayOfWeek.SUNDAY) {
            tipoDia = tipoFinDeSemana;
        } else {
            tipoDia = tipoLaboral;
        }
        String descripcion = fecha.getDayOfWeek().toString();
        
        Calendario diaCalendario = new Calendario(fecha, tipoDia, descripcion, pais);
        calendarioAGuardar.add(diaCalendario);
        
        fecha = fecha.plusDays(1);
    }

    calendarioRepository.saveAll(calendarioAGuardar);

    return calendarioAGuardar;
}

public boolean listarCalendarioPrincipal(int paisId, int año) {
    try {
        
    
    List<Map<String, Object>> festivos = logica(paisId, año);

    Optional<Pais> paisOpt = paisService.obtener(paisId);
    if (paisOpt.isEmpty()) throw new RuntimeException("País no encontrado");
    Pais pais = paisOpt.get();

    Tipo tipoFestivo = tipoService.obtener(3);
    Tipo tipoLaboral = tipoService.obtener(1);
    if (tipoFestivo == null || tipoLaboral == null)
        throw new RuntimeException("Tipos de día no encontrados");

    Set<LocalDate> fechasFestivas = festivos.stream()
            .map(f -> (LocalDate) f.get("fecha"))
            .collect(Collectors.toSet());

    List<Calendario> calendario = new ArrayList<>();

    LocalDate fecha = LocalDate.of(año, 1, 1);
    LocalDate fin = LocalDate.of(año, 12, 31);
    int id = 1;

    while (!fecha.isAfter(fin)) {

        Tipo tipo = fechasFestivas.contains(fecha) ? tipoFestivo : tipoLaboral;
        String descripcion = fecha.getDayOfWeek().toString();

        Calendario dia = new Calendario(fecha, tipo, descripcion, pais);
        calendario.add(dia);

        fecha = fecha.plusDays(1);
        id++;
    }

    // Guardar todas las entidades
    calendarioRepository.saveAll(calendario);

    return true;
    } catch (Exception e) {
        return false;
    }
}





}
