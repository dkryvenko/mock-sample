import java.util.List;

import static junit.framework.Assert.*;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;

/**
 * The unit tests for DefaultSimpleStack class. Mockito mock framework is used
 * to demonstrate basic work flow of mock objects usage.
 * 
 * The reasons why mock objects should be used in unit tests instead of real dependencies: 
 * 
 * 1. using real dependency may significantly reduce performance of test 
 * 2. real dependency may be under development and may have defects
 * 3. real dependency may be unknown (interface is known only but not component which implements it)
 * 4. real dependency may not be available from test context (remote web service which requires vpn connection)
 * 
 * The basic cases for mock object:
 * 
 * 1. create mock object for some component (mock(SomeClassOrInterface.class))
 * 2. program behavior of mock object (when(...).then(...))
 * 3. verify interaction with mock object (verify(...).doSomething(...))  
 */
public class DefaultSimpleStackTest<T> {

	// create mock object which implements List interface
	List mockList = mock(List.class);

	DefaultSimpleStack<Integer> stack = new DefaultSimpleStack<Integer>(mockList);

	@Test(expected = IllegalArgumentException.class)
	public void testCtorWithNullArg() {
		new DefaultSimpleStack<T>(null);
	}

	@Test
	public void testPush() {
		// create argument captor for Integer class
		ArgumentCaptor<Integer> arg = ArgumentCaptor.forClass(Integer.class);

		// call testing object
		stack.push(5);
		stack.push(3);
		stack.push(7);

		// verify that method add() was called third time and capture passes arguments
		verify(mockList, times(3)).add(arg.capture());

		// get list of arguments which were passed to method add() and assert it
		List<Integer> args = arg.getAllValues();
		assertEquals(5, args.get(0).intValue());
		assertEquals(3, args.get(1).intValue());
		assertEquals(7, args.get(2).intValue());

	}

	@Test
	public void testPop() {
		// program behavior of methods size() and get() of mock list
		when(mockList.size()).thenReturn(3).thenReturn(2).thenReturn(1);
		when(mockList.get(2)).thenReturn(7);
		when(mockList.get(1)).thenReturn(3);
		when(mockList.get(0)).thenReturn(5);

		// call testing object and assert results
		assertEquals(7, stack.pop().intValue());
		assertEquals(3, stack.pop().intValue());
		assertEquals(5, stack.pop().intValue());
	}

}
