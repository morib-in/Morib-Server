package org.sopt.jaksim.category.dto;

import org.sopt.jaksim.category.domain.Category;
import org.sopt.jaksim.mset.domain.Mset;

import java.util.List;

public record CategoryMsetLinkResponse(
        Category category,
        List<Mset> msetList
) {
    public static CategoryMsetLinkResponse of (Category category, List<Mset> msetList) {
        return new CategoryMsetLinkResponse(category, msetList);
    }
}
