package demo;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
//@Scope(scopeName = "singleton")
@Scope(scopeName = "prototype")
public class Doctor implements Staff, BeanNameAware {
    private String qualification;

    @Override
    public String toString() {
        return "Doctor{" +
                "qualification='" + qualification + '\'' +
                '}';
    }

    //    private Nurse nurse;

//    public Nurse getNurse() {
//        return nurse;
//    }

//    public void setNurse(Nurse nurse) {
//        this.nurse = nurse;
//    }

//    public Doctor(String qualification) {
//        this.qualification = qualification;
//    }

    public void assist(){
        System.out.println("doctor");
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("bean name method");
    }
    @PostConstruct
    public void postconstruct(){
        System.out.println("post construct method");
    }
}
