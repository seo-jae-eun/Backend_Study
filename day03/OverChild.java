public class OverChild extends OverParents{
    @Override
    Integer sum(Integer num01, Integer num02) {
        Integer result;
        result = num01 + num02 + 10;

        return result;
    }
}
