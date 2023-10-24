package br.com.unipar.BullkApp.util;

import br.com.unipar.BullkApp.enums.SexoENUM;
import br.com.unipar.BullkApp.enums.TipoUsuarioENUM;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Data
public class MapperUsuario {
    private String nome;
    private Instant dtNascimento;
    @Enumerated(EnumType.STRING)
    private SexoENUM sexo;
    private String celular;
    private String email;
    @Enumerated(EnumType.STRING)
    private TipoUsuarioENUM tpUsuario;
    private boolean status;
    private String senha;

    public void setDtNascimento(Date dtNascimento) throws Exception {
        this.dtNascimento = dtNascimento.toInstant().plus(3, ChronoUnit.HOURS);
    }

//    public Date getDtNascimento() {
//        return dtNascimento;
//    }

    //    public static Date formatDate(Instant value) {
//        try {
//            SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy HH:mm:ss");
//            return new Date(formatter.format(value));
//        } catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//        return null;
//    }
}

