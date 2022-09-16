package platform.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import platform.persistence.CodeRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CodesService {
    @Autowired
    CodeRepository codeRepository;

    private List<CodeDto> codesList = new ArrayList<>();

    private static final String DATE_FORMATTER = "yyyy-MM-dd HH:mm:ss";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);

    public Codes searchCode(String id){
        return codeRepository.findCodesById(id);
    }


    public CodeDto findCode(String id) {
        Codes codes1 = codeRepository.findCodesById(id);

        if (codes1 == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "UUID not found!");
        else if (codes1.isRestricted()) {
            if (!viewable(codes1.getDate(), codes1.getTime()) || codes1.getViews() <= 0) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "expired");

            }
            codes1.setViews(codes1.getViews() - 1);
            int seconds = (int) ChronoUnit.SECONDS.between(codes1.getDate(), LocalDateTime.now());
            codes1.setTime(codes1.getTime() - seconds);
            codeRepository.save(codes1);
            return new CodeDto(codes1.getDate().format(formatter), codes1.getCode(), codes1.getTime(), codes1.getViews());

        } else if (codes1.isTimeRestricted()) {
            if (!viewable(codes1.getDate(), codes1.getTime())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "time expired");
            }
            int seconds = (int) ChronoUnit.SECONDS.between(codes1.getDate(), LocalDateTime.now());
            codes1.setTime(codes1.getTime() - seconds);
            codeRepository.save(codes1);
            return new CodeDto(codes1);
        } else if (codes1.isViewRestricted()) {
            if (codes1.getViews() <= 0) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "view expired");

            }
            codes1.setViews(codes1.getViews() - 1);
            codeRepository.save(codes1);
            return new CodeDto(codes1);
        }
        codeRepository.save(codes1);
        return new CodeDto(codes1.getDate().format(formatter), codes1.getCode(), codes1.getTime(), codes1.getViews());
    }


    public List<CodeDto> findLast() {
        codesList.clear();
        List<Codes> codesList1 = codeRepository.findLatest();
        for (Codes codes1 : codesList1) {
            if (codes1.isViewRestricted() || codes1.isTimeRestricted()) continue;
            CodeDto codeDto = new CodeDto(codes1.getDate().format(formatter), codes1.getCode(), codes1.getTime(), codes1.getViews());
            codesList.add(codeDto);
            if (codesList.size() == 10) break;
        }
        return codesList;
    }


    public Map<String, String> postCode(Codes codes1) {
        UUID uuid = UUID.randomUUID();
        codes1.setDate(LocalDateTime.now());
        codes1.setId(uuid.toString());
        codes1.setTimeRestricted(codes1.getTime() >= 1);
        codes1.setViewRestricted(codes1.getViews() >= 1);
        codes1.setRestricted(codes1.getTime() >= 1 && codes1.getViews() >= 1);
        System.out.println(codes1);
        Codes codes = codeRepository.save(codes1);
        return Map.of("id", codes.getId());
    }

    public boolean viewable(LocalDateTime upload, int time) {
        System.out.println(upload + " " + time + " ");
        LocalDateTime elapsed = upload.plusSeconds(time);

        int timeDiff = elapsed.compareTo(LocalDateTime.now()); // 0, time1 and time1 are equal
        System.out.println(elapsed + " " + LocalDateTime.now());
        System.out.println(timeDiff);

        return timeDiff >= 0;
    }
}