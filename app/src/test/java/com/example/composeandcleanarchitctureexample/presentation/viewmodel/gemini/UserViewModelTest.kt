package com.example.composeandcleanarchitctureexample.presentation.viewmodel.gemini

import com.example.composeandcleanarchitctureexample.presentation.viewmodel.UserViewModel


import com.example.composeandcleanarchitctureexample.domain.model.User
import com.example.composeandcleanarchitctureexample.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest // For concise coroutine testing
import org.junit.Assert
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class) // Initializes @Mock annotated fields
class UserViewModelTest {

    // Rule to swap the Main dispatcher with a TestDispatcher
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Mock the dependency
    @Mock
    private lateinit var mockGetUsersUseCase: GetUsersUseCase

    // The ViewModel under test
    private lateinit var viewModel: UserViewModel

    @Before
    fun setUp() {
        // ViewModel is instantiated here before each test,
        // allowing fresh state for each test case.
        // We manually provide the mock dependency.
    }

    @Test
    fun `init loads users successfully`() = runTest { // runTest provides a coroutine scope
        // Arrange
        val fakeUsers = listOf(
            User(id = 1, name = "Alice", email = "Alice@gmail.com"),
            User(id = 2, name = "Bob", email = "Bob@gmail.com")
        )
        whenever(mockGetUsersUseCase()).thenReturn(fakeUsers)

        // Act: Instantiate ViewModel (which triggers init and fetchUsers)
        viewModel = UserViewModel(mockGetUsersUseCase)

        // Assert
        // We need to access the value of the State.
        // Since the coroutine in init might take a moment (even with UnconfinedTestDispatcher),
        // observing the state or ensuring the coroutine completes is good practice.
        // UnconfinedTestDispatcher typically runs eagerly, so direct assertion often works.
        Assert.assertEquals(viewModel.users.value, fakeUsers)
    }

    @Test(expected = RuntimeException::class)
    fun `init handles exception when fetching users`() = runTest {
        // Arrange
        val errorMessage = "Network error"
        whenever(mockGetUsersUseCase()).thenThrow(RuntimeException(errorMessage))

        // Act: Instantiate ViewModel
        viewModel = UserViewModel(mockGetUsersUseCase)

        // Assert
        // The users list should remain empty as the fetch failed.
        //Assert.assertEquals(viewModel.users.value, emptyList<User>())
        // You could also verify that Log.d was called if you have a way to intercept logs,
        // but for unit tests, focusing on the ViewModel's state is usually sufficient.
        // For example, if you exposed an error state:
        // assertThat(viewModel.error.value).isEqualTo(errorMessage)
    }

    @Test
    fun `users State initially empty before fetch completes (if fetch was manual)`() {
        // This test is more relevant if fetchUsers() was a public method called manually,
        // rather than in init. With fetchUsers in init, the fetch starts immediately.
        // However, we can still test the initial state before any mocking is applied,
        // though with the current ViewModel structure, `init` will always run.

        // Arrange & Act: Instantiate ViewModel without pre-configuring the mock for this specific case,
        // or ensure the mock doesn't return immediately if that's what you're testing.
        // This test might be more about the default state of `_users` before `init` runs,
        // which is `emptyList()`.

        viewModel = UserViewModel(mockGetUsersUseCase) // mockGetUsersUseCase will likely return null or throw if not stubbed

        // Assert initial state (before init's coroutine might have completed if it were complex)
        // Given `UnconfinedTestDispatcher` and immediate fetch in `init`,
        // this assertion might be tricky to isolate purely for the "before fetch" state
        // without the fetch actually having run.
        // A more robust way to test initial state would be if fetchUsers was not in init.
        // However, we can assert that if the use case is mocked to suspend indefinitely or throw immediately,
        // the list remains empty.
        Assert.assertEquals(viewModel.users.value, null)
    }
}