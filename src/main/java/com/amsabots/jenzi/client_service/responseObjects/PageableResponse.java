package com.amsabots.jenzi.client_service.responseObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author andrew mititi on Date 11/20/21
 * @Project lameck-client-service
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageableResponse<T> {
    private List<T> data;
    private int currentPageSize;
    private int currentPage;
}
