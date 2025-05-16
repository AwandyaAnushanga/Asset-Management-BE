package com.nexeyo.erp.DisposeAsset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dispose-asset")
public class DisposeAssetController {

    @Autowired
    private DisposeAssetService disposeAssetService;


}
