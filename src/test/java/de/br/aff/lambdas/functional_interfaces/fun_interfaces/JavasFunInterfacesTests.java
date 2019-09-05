package de.br.aff.lambdas.functional_interfaces.fun_interfaces;

import de.br.aff.lambdas.domain.Person;
import org.junit.Test;

import java.util.function.*;

public class JavasFunInterfacesTests {

    //CONSUMERS:

    @Test
    public void thatConsumerDoesSomeWorkWithoutReturningValue() {
        //doesn't return any value
        Consumer<String> consumer = System.out::println;
        consumer.accept("String to be printed");
    }

    @Test
    public void thatBiConsumerAcceptsTwoArgumentsAndDoesntReturnValue() {
        BiConsumer<String, Integer> biConsumer = (a, b) -> System.out.println("Concatenated: " + a + b);
        biConsumer.accept("String to be printed ", 1);
    }

    //SUPPLIER:

    @Test
    public void thatSupplierDoesntTakeAnyParameterButReturnsValue() {
        Supplier<Person> supplierFromDefaultConstructor = Person::new; //only if we dont have parameters in constructor
        assert supplierFromDefaultConstructor.get() != null;

        Supplier<Person> supplier = () -> new Person("last", "first", 83);
        assert supplier.get() != null;
    }

    //FUNCTIONS:

    @Test
    public void thatFunctionInterfaceTakesArgumentOfOneTypeAndTransformsItToAnotherOrTheSame() {

        Function<Integer, String> funAnotherReturnType = Object::toString; // the same as a -> a.toString();

        assert funAnotherReturnType.apply(3).equalsIgnoreCase("3");

        Function<Integer, Integer> funTheSameReturnType = a -> a * a;

        assert funTheSameReturnType.apply(3) == 3 * 3;

        //identity is static function of Function interface that returns a reference to Function that always returns its input argument
        assert Function.<Integer>identity().apply(3) == 3;

    }

    @Test
    public void thatUnaryOperatorReturnsTheSameTypeItReceivedAsArgument() {
        UnaryOperator<Integer> unary = (a) -> a * a;
        assert unary.apply(3) == 3 * 3;
    }

    @Test
    public void thatBiFunctionInterfaceTakesTwoArgumentsOfDifferentOrTheSameTypesAndTransformsItToAnotherOrTheSameType() {

        BiFunction<Integer, Double, String> bi = (a, b) -> a.toString() + b.toString();

        assert bi.apply(3, 3.1).equalsIgnoreCase("33.1");

    }

    //BinaryOperator specialization of BiFunction where all parameters are of the same type
    @Test
    public void thatBinaryOperatorTakesTwoArgumentsOfTheSameTypeAndReturnsThatType() {

        BinaryOperator<Integer> biOpSums = (a1, a2) -> a1 + a2;
        assert biOpSums.apply(1, 2) == 3;

        BinaryOperator<Integer> moreVerboseBiOpSums = (a1, a2) -> Integer.sum(a1, a2);
        assert moreVerboseBiOpSums.apply(1, 2) == 3;

        BinaryOperator<Integer> biOpLambdaSums = Integer::sum; //same as (a1,a2)->Integer.sum(a1,a2)
        assert biOpLambdaSums.apply(1, 2) == 3;

        BinaryOperator<String> biOpConcatenator = (a1, a2) -> a1 + a2;
        assert biOpConcatenator.apply("it", "concatenates").equalsIgnoreCase("itconcatenates");
    }

    //PREDICATES:
    @Test
    public void thatPredicateAcceptsDefinedTypeParameterAndReturnsBoolean() {
        Predicate<String> p = s -> s.length() > 20;
        assert !p.test("asd");
    }
}
