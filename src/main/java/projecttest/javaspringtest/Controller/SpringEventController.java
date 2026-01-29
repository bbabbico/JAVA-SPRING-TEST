package projecttest.javaspringtest.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import projecttest.javaspringtest.Database.JPA.JPAMember;
import projecttest.javaspringtest.Event.EventService;

@RestController
@RequiredArgsConstructor
public class SpringEventController {

    private final EventService eventService;

    @GetMapping("/Event")
    public String Event() {
        JPAMember member = eventService.createMember(new JPAMember(null,"loginlogin","ppooop","USER"));
        return member.toString();
    }
}
