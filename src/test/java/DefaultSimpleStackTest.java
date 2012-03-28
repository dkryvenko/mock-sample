import java.util.List;

import static junit.framework.Assert.*;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;

public class DefaultSimpleStackTest<T> {

	@SuppressWarnings({ "rawtypes" })
	List mockList = mock(List.class);
	@SuppressWarnings("unchecked")
	DefaultSimpleStack<Integer> stack = new DefaultSimpleStack<Integer>(mockList);

	@Test(expected = IllegalArgumentException.class)
	public void testCtorWithNullArg() {
		new DefaultSimpleStack<T>(null);
	}

	@Test
	public void testPush() {
		ArgumentCaptor<Integer> arg = ArgumentCaptor.forClass(Integer.class);

		stack.push(5);
		stack.push(3);
		stack.push(7);

		verify(mockList, times(3)).add(arg.capture());
		List<Integer> args = arg.getAllValues();
		assertEquals(5, args.get(0).intValue());
		assertEquals(3, args.get(1).intValue());
		assertEquals(7, args.get(2).intValue());

	}

	@Test
	public void testPop() {
		// method size() will be called third times
		when(mockList.size()).thenReturn(3).thenReturn(2).thenReturn(1);
		when(mockList.get(2)).thenReturn(7);
		when(mockList.get(1)).thenReturn(3);
		when(mockList.get(0)).thenReturn(5);
		
		assertEquals(7, stack.pop().intValue());
		assertEquals(3, stack.pop().intValue());
		assertEquals(5, stack.pop().intValue());
	}

}
