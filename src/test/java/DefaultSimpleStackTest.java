import java.util.List;

import static junit.framework.Assert.*;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;

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

		// verify that method add() was called third time and capture passed arguments
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
