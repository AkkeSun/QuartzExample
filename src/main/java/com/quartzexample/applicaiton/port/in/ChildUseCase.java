package com.quartzexample.applicaiton.port.in;

import com.quartzexample.domain.TestDTO;

public interface ChildUseCase {

    void process(TestDTO dto);
}
