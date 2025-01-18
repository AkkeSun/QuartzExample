package com.quartzexample.applicaiton.service;

import com.quartzexample.applicaiton.port.in.ChildUseCase;
import com.quartzexample.domain.TestDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
class ChildService implements ChildUseCase {

    @Override
    public void process(TestDTO dto) {
        if (dto.getName().equals("error")) {
            throw new RuntimeException("error");
        }
        log.info("hello " + dto.getName());
    }
}
