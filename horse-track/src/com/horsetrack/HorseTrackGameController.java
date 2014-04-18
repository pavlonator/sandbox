package com.horsetrack;

import java.util.Map;

/**
 * @author Pavlo Cherkashyn
 */
public class HorseTrackGameController {
    private HorseTrackGameState state;
    private Presenter presenter;

    public HorseTrackGameController(Presenter presenter) {
        this.state = new HorseTrackGameState();
        state.initialize();
        this.presenter = presenter;
    }

    public void onGameStart(){
        presenter.listInventory(state);
        presenter.listHorses(state);
    }

    public void restockTheCashInventory() {
        Map<Integer, Integer> inventory = state.getInventory();
        inventory.put(100, 10);
        inventory.put(50, 10);
        inventory.put(20, 10);
        inventory.put(10, 10);
        inventory.put(5, 10);
        inventory.put(1, 10);

        presenter.listInventory(state);
        presenter.listHorses(state);
    }

    public void performWinnerSetupCommand(String line) throws ValidationException {
        try {
        int horseNumber = Integer.parseInt(line.substring(1).trim());
        ValidationUtils.validateHorseNumber(state, horseNumber);
        state.setWinner(horseNumber);
        presenter.listInventory(state);
        presenter.listHorses(state);
        } catch (NumberFormatException nfe){
            ValidationUtils.handleInvalidHorseNumber(line.substring(1).trim());
        }

    }

    public void performBetAttempt(String line) throws ValidationException {
        int horseNumber = Integer.parseInt(new String(new char[]{line.charAt(0)}));
        ValidationUtils.validateHorseNumber(state, horseNumber);

        String amountStr = line.substring(1).trim();
        int betAmount = ValidationUtils.validateAmountValue(line, amountStr);



        if (state.isWinningHorseNumber(horseNumber)) {
            handleWinSituation(betAmount, horseNumber);
        } else {
//            adding lost money to inventory is not needed by requirement
//            MoneyUtils.putMoneyToInventory(betAmount, state.getInventory());
            handleLoseSituation(horseNumber);
        }
        presenter.listInventory(state);
        presenter.listHorses(state);
    }

    public void exit() {
        //nothing for the moment
    }


    private void handleLoseSituation(int horseNumber) {
        presenter.showMessage("No Payout: " + state.getHorses().get(horseNumber - 1).getName());
    }

    private void handleWinSituation(int betAmount, int horseNumber) {
        int odds = state.getHorses().get(horseNumber - 1).getOdds();
        int payoutAmount = betAmount * odds;
        try {
            Map<Integer, Integer> billsToDeliver = MoneyUtils.dispenseAmount(state.getInventory(), payoutAmount);
            presenter.listDispenseBills(state.getHorses().get(horseNumber - 1).getName(), payoutAmount, billsToDeliver);
        } catch (InsufficientFundsException ife) {
            presenter.showErrorMessage(ife.getMessage());
        }

    }
}
