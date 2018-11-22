package com.yongf.android.contactsapp.domain.interactor;

import com.yongf.android.contactsapp.domain.executor.base.Executor;
import com.yongf.android.contactsapp.domain.executor.base.MainThread;
import com.yongf.android.contactsapp.domain.executor.impl.MainThreadImpl;
import com.yongf.android.contactsapp.domain.model.ContactInfo;
import com.yongf.android.contactsapp.domain.repository.MarvelRepository;
import com.yongf.android.contactsapp.util.ContactsHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * @author scottwang1996@qq.com
 * @since 2018/11/22.
 */
@RunWith(RobolectricTestRunner.class)
public class GetMarvelHeroesTest {

    @Mock
    Executor mockExecutor;
    @Mock
    GetMarvelHeroes.Callback mockCallback;
    @Mock
    MarvelRepository mockRepository;

    private MainThread mMainThread;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mMainThread = MainThreadImpl.getInstance();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetMarvelHeroesSuccess() throws Exception {
        List<ContactInfo> infos = ContactsHelper.mockData();

        when(mockRepository.getMarvelData()).thenReturn(infos);

        GetMarvelHeroes interactor = new GetMarvelHeroes(mockExecutor,
                mMainThread, mockCallback, mockRepository);
        interactor.run();

        verify(mockRepository).getMarvelData();
        verifyNoMoreInteractions(mockRepository);
        verify(mockCallback).onHeroesRetrieved(infos);
    }

    @Test
    public void testGetMarvelHeroesError1() throws Exception {
        when(mockRepository.getMarvelData()).thenReturn(null);

        GetMarvelHeroes interactor = new GetMarvelHeroes(mockExecutor,
                mMainThread, mockCallback, mockRepository);
        interactor.run();

        verify(mockRepository).getMarvelData();
        verifyNoMoreInteractions(mockRepository);
        verify(mockCallback).onRetrievalFailed("OOPS, something is wrong -_-||");
    }

    @Test
    public void testGetMarvelHeroesError2() throws Exception {
        when(mockRepository.getMarvelData()).thenReturn(new ArrayList<ContactInfo>());

        GetMarvelHeroes interactor = new GetMarvelHeroes(mockExecutor,
                mMainThread, mockCallback, mockRepository);
        interactor.run();

        verify(mockRepository).getMarvelData();
        verifyNoMoreInteractions(mockRepository);
        verify(mockCallback).onRetrievalFailed("OOPS, something is wrong -_-||");
    }
}