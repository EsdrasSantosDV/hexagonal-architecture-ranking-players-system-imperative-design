package hexagonal.architecture.esdras.adapter.in.rest.common;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PaginationResponse<T> {
    private final List<T> items;
    private final long totalPages;
    private final long totalItems;

    public PaginationResponse(List<T> items, long totalPages, long totalItems) {
        this.items = items;
        this.totalPages = totalPages;
        this.totalItems = totalItems;
    }
}
