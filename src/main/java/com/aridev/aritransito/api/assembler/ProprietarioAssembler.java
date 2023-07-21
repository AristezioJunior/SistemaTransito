package com.aridev.aritransito.api.assembler;

import com.aridev.aritransito.api.model.ProprietarioModel;
import com.aridev.aritransito.api.model.VeiculoModel;
import com.aridev.aritransito.api.model.input.ProprietarioInput;
import com.aridev.aritransito.domain.model.Proprietario;
import com.aridev.aritransito.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ProprietarioAssembler {

    private final ModelMapper modelMapper;

    public Proprietario toEntity(ProprietarioInput proprietarioInput) {
        return modelMapper.map(proprietarioInput, Proprietario.class);
    }

    public ProprietarioModel toModel(Proprietario proprietario) {
        return modelMapper.map(proprietario, ProprietarioModel.class);
    }

    public List<ProprietarioModel> toCollectionModel(List<Proprietario> proprietarios) {
        return proprietarios.stream()
                .map(this::toModel)
                .toList();
    }

}
