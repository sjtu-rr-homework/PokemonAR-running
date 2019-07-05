package pl.piomin.services.organization.fallback;

import org.springframework.stereotype.Component;
import pl.piomin.services.organization.client.DepartmentClient;
import pl.piomin.services.organization.model.Department;

import java.util.List;
@Component
public class DepartmentClientHystrix implements DepartmentClient {
    @Override
    public List<Department> findByOrganization(Long organizationId) {
        return null;
    }

    @Override
    public List<Department> findByOrganizationWithEmployees(Long organizationId) {
        return null;
    }
}
