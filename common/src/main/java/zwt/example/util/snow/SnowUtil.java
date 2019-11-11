package zwt.example.util.snow;

import cn.org.bjca.ec.platform.utils.NumberStringUtil;
import com.sun.deploy.config.Config;
import zwt.example.util.ConfigUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @Author: zwt
 * @Date: Create in 17:04 2019-10-24
 */
public class SnowUtil {
    private static Sequence sequence;
    private Long dtaCenterId = 0L;
    public static final String BUSINESS_NO = "00";
    public static final String KEY_NO = "01";
    private SnowUtil(){
        String snowDtaCenterNo = null;
        try {
            snowDtaCenterNo = ConfigUtil.get("data.center.id");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(snowDtaCenterNo !=null){
            dtaCenterId = Long.valueOf(snowDtaCenterNo);
            sequence = new Sequence(dtaCenterId);
        }else{
            sequence = new Sequence(dtaCenterId);
        }
    }

    private static class SingletonInstance {
        private static final SnowUtil INSTANCE = new SnowUtil();
    }

    /**
     *
     * @param type 2位类型
     * @return
     */
     public static String getUniqueNo(String type){
         if(type == null || type.length()<2){
             type = type == null ? "00" : NumberStringUtil.addLeftZero(type.trim(),2);
         }
         SnowUtil instance = SingletonInstance.INSTANCE;
        return instance.getNo(type);
    }

    /**
     * 32位
     * @param type
     * @return
     */
    private String getNo(String type){
        String current = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return current + type + NumberStringUtil.addLeftZero(String.valueOf(dtaCenterId),4) +
                NumberStringUtil.addLeftZero(String.valueOf(sequence.nextId()),18);
    }
}
