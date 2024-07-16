package org.sopt.jaksim.mset.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sopt.jaksim.category.dto.CategoryCreateRequest;
import org.sopt.jaksim.global.exception.NotFoundException;
import org.sopt.jaksim.global.message.ErrorMessage;
import org.sopt.jaksim.mset.domain.CategoryMset;
import org.sopt.jaksim.mset.domain.Mset;
import org.sopt.jaksim.mset.repository.CategoryMsetRepository;
import org.sopt.jaksim.mset.repository.MsetRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MsetService {
    private final MsetRepository msetRepository;
    private final CategoryMsetRepository categoryMsetRepository;

    public void createByCategory(CategoryCreateRequest categoryCreateRequest, Long categoryId) {
        List<Mset> msetList = categoryCreateRequest.msets();
        for (Mset mset : msetList) {
            mset = msetRepository.save(Mset.create(mset.getName(), mset.getUrl()));
            categoryMsetRepository.save(CategoryMset.create(categoryId, mset.getId()));
        }
    }

    public Mset getMsetById(Long msetId) {
        return msetRepository.findById(msetId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.NOT_FOUND)
        );
    }

    public void delete(List<Long> msetIdList) {
        List<Mset> msetList = msetRepository.findAllById(msetIdList);
        if (msetIdList.isEmpty()) {
            throw new NotFoundException(ErrorMessage.NOT_FOUND);
        }
        msetRepository.deleteAll(msetList);
    }
}
