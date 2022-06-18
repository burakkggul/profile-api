package tr.com.burakgul.profileapi.core.helper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DTOMapper {

    private final ModelMapper modelMapper;

    public <T extends Object,S extends Object> T mapModel(S source, Class<T> target){
        return this.modelMapper.map(source,target);
    }

    public <S extends Object,T extends Object> List<T> mapListModel(List<S> source, Class<T> target){
        List<T> mappedList = source
                .stream()
                .map(element -> this.mapModel(element,target))
                .collect(Collectors.toList());
        return mappedList;
    }
}
