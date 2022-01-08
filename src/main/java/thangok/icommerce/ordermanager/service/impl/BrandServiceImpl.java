package thangok.icommerce.ordermanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import thangok.icommerce.ordermanager.aop.executiontime.LogExecutionTime;
import thangok.icommerce.ordermanager.aop.io.LogIO;
import thangok.icommerce.ordermanager.dto.BrandDTO;
import thangok.icommerce.ordermanager.entity.Brand;
import thangok.icommerce.ordermanager.repository.BrandRepository;
import thangok.icommerce.ordermanager.service.BrandService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandRepository brandRepository;

    @Override
    public Page<BrandDTO> getAllBrands() {
        List<Brand> brandList = brandRepository.findAll();
        if (brandList.isEmpty()) {
            return Page.empty();
        }

        List<BrandDTO> result = new ArrayList<>();
        brandList.stream().map(x -> new BrandDTO() {{
            setBrandCode(x.getBrandCode());
            setBrandName(x.getBrandName());
            setDescription(x.getDescription());
        }}).forEach(result::add);

        return new PageImpl<>(result);
    }

    @Override
    public Page<BrandDTO> searchBrands(String searchText) {
        return null;
    }

    @Override
    @LogExecutionTime
    @LogIO
    public Optional<BrandDTO> getByCode(String brandCode) {
        Optional<Brand> brand = brandRepository.findByBrandCode(brandCode);
        if (!brand.isPresent()) {
            return Optional.empty();
        }

        Brand result = brand.get();
        return Optional.of(new BrandDTO() {{
            setBrandCode(result.getBrandCode());
            setBrandName(result.getBrandName());
            setDescription(result.getDescription());
        }});
    }

    @LogExecutionTime
    @LogIO
    @Override
    public BrandDTO createBrand(BrandDTO brandDTO) {
        Brand brand = new Brand();
        brand.setBrandCode(brandDTO.getBrandCode());
        brand.setBrandName(brandDTO.getBrandName());
        brand.setDescription(brandDTO.getDescription());

        Brand result = brandRepository.save(brand);
        return new BrandDTO() {{
            setBrandCode(result.getBrandCode());
            setBrandName(result.getBrandName());
            setDescription(result.getDescription());
        }};
    }

    @Override
    public BrandDTO updateBrand(BrandDTO brandDTO) {
        return null;
    }

    @Override
    public BrandDTO deleteBrand(String brandCode) {
        return null;
    }
}
