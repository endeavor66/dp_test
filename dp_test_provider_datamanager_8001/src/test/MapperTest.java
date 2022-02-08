
import com.example.dp.service.DatePlatFormService;
import com.example.dp.serviceImpl.DatePlatFormServiceImpl;
import com.example.dp.vo.ResponseVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MapperTest {
    @Autowired
    DatePlatFormService datePlatFormService;

    @Test
    public void loginInfoTest(){
        ResponseVO responseVO=datePlatFormService.loginInfo("benson");
        System.out.println(responseVO);
    }

}
