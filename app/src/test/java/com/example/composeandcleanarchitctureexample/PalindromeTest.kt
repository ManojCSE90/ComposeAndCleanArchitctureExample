package com.example.composeandcleanarchitctureexample

import org.junit.Assert.*
import org.junit.Test

class PalindromeTest {

    @Test
    fun `isPalindrome should return true for a palindrome string`() {
        assertTrue(isPalindrome("A man, a plan, a canal: Panama"))
    }

    @Test
    fun `isPalindrome should return false for a non-palindrome string`() {
        assertFalse(isPalindrome("hello"))
    }

    @Test
    fun `isPalindrome should return true for an empty string`() {
        assertTrue(isPalindrome(""))
    }

    @Test
    fun `isPalindrome should return true for a string with only spaces`() {
        assertTrue(isPalindrome("   "))
    }

    @Test
    fun `isPalindrome should return true for a single character string`() {
        assertTrue(isPalindrome("a"))
    }

    @Test
    fun `isPalindrome should handle numbers and letters`() {
        assertTrue(isPalindrome("12321"))
    }

    @Test
    fun `isPalindrome should handle mixed case`() {
        assertTrue(isPalindrome("Racecar"))
    }
}