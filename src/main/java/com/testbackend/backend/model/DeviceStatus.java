package com.testbackend.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceStatus {
    private boolean quatHut;      // Quạt hút
    private boolean quatThoi;     // Quạt thổi
    private boolean soNongLanh;   // Sò lạnh
    private boolean mayBom;       // Máy bơm
    private boolean rem;          // Rèm
}