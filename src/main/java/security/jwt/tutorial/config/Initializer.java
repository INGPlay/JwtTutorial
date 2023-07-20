package security.jwt.tutorial.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import security.jwt.tutorial.service.InitService;

@Slf4j
@Component
@RequiredArgsConstructor
public class Initializer implements InitializingBean {

    private final InitService initService;
    @Override
    public void afterPropertiesSet() throws Exception {
        initService.init();
    }
}
