package foo.bar.stuff;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.Inet4Address;
import java.net.UnknownHostException;

@Controller
public class WhoAmI {

    @RequestMapping("/whoami")
    @ResponseBody
    public String whoAmI() throws UnknownHostException {
        return Inet4Address.getLocalHost().getHostName();
    }

}
