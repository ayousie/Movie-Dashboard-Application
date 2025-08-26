package fawry.task.admin.dashboard.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageResponseDTO<T> {
    private List<T> content;
    private long totalElements;
    private int totalPages;
    private int page;
    private int size;
}
