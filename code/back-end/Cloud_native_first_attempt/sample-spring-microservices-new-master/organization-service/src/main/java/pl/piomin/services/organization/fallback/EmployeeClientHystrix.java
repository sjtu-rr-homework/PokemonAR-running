package pl.piomin.services.organization.fallback;


import org.springframework.stereotype.Component;
import pl.piomin.services.organization.client.EmployeeClient;
import pl.piomin.services.organization.model.Employee;

import java.util.List;

@Component
public class EmployeeClientHystrix implements EmployeeClient {

    @Override
    public List<Employee> findByOrganization(Long organizationId) {
        return null;
    }
}
