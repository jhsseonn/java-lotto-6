package lotto.service;

import lotto.common.LottoFinalConsts;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoRank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

class LottoServiceTest implements LottoFinalConsts {

    private Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
    private int lottoPurchase = LOTTO_PURCHASE_DIVIDABLE;
    private List<Lotto> lottos = new ArrayList<>(List.of(lotto));
    private HashMap<LottoRank, Integer> lottoRanks = new HashMap<>();
    private LottoMachine lottoMachine = new LottoMachine(lottoPurchase, lottos, lottoRanks);
    private LottoService lottoService = new LottoService();
    private List<Integer> winningNumbers = new ArrayList<>(List.of(3, 4, 5, 6, 7, 8));
    private String winningNumber = "1,2,3,4,5,6";
    private String bonusNumber = "7";

    @BeforeEach
    void 로또_머신을_초기화한다() {
        lottoMachine = new LottoMachine(lottoPurchase, lottos, lottoRanks);
    }

    @Test
    void 사용자가_입력한_당첨번호와_보너스번호를_로또_머신에_저장한다() {
        String[] winning = winningNumber.split(LOTTO_WINNING_SPLIT);
        for(String number:winning){
            lottoMachine.updateLottoWinningNumbers(Integer.parseInt(number));
        }
        lottoMachine.updateLottoBonusNumber(Integer.parseInt(bonusNumber));

        assertThat(lottoMachine.getLottoWinningNumbers()).isEqualTo(List.of(1,2,3,4,5,6));
        assertThat(lottoMachine.getLottoBonusNumber()).isEqualTo(7);
    }

    @Test
    void 사용자가_입력한_당첨번호가_6개가_아닌_경우_예외를_발생한다() {
        String winningNumber = "1,2,3,4,5,6,7";
        String[] winning = winningNumber.split(LOTTO_WINNING_SPLIT);
        assertThatThrownBy(() -> lottoService.validateWinningSize(winning))
                .isInstanceOf(ArrayIndexOutOfBoundsException.class);
    }

    @Test
    void 사용자가_입력한_보너스_번호가_이미_당첨번호에_존재할_경우_예외를_발생한다() {
        assertThatThrownBy(() -> lottoService.isBonusNumberAlreadyExist(winningNumbers, 6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 사용자가_입력한_당첨번호와_보너스번호의_범위가_1부터_45까지가_아닌_경우_예외를_발생한다() {
        assertThatThrownBy(() -> lottoService.isNumberOutOfRange("66"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void saveRankCount() {
    }

    @Test
    void getCorrectCount() {
    }

    @Test
    void getLottoRank() {
    }

    @Test
    void getLottoReturn() {
    }

    @Test
    void computeLottoReturnRate() {
    }
}