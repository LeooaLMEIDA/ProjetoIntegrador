package br.com.unipar.BullkApp.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageableDTO {
    private List<Object> data;
    private int actualPage;
    private int total;
}
