package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import lotto.service.LottoService;
import lotto.view.LottoView;

public class LottoController {

    private LottoView lottoView = new LottoView();
    private LottoService lottoService = new LottoService();

    public void runLotto(){
        String lottoPurchase = lottoView.inputLottoPurchase();
        int lottoPurchaseReturn = lottoService.getLottoCount(lottoPurchase);
        lottoView.printLottoPurchase(lottoPurchaseReturn);

    }
}
