/**
 * Copyright (c) 2016-present, RxJava Contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See
 * the License for the specific language governing permissions and limitations under the License.
 */

package io.reactivex.singles;

import io.reactivex.Single;
import org.junit.Test;

import java.util.concurrent.Callable;

public class TestSingleTest {

    @Test
    public void testFrom() {
        final String expected = "Hello!";
        Single<String> original = Single.fromCallable(new Callable<String>() {
            @Override
            public String call() {
                return expected;
            }
        });

        TestSingle<String> testSingle = TestSingle.from(original);
        testSingle.test().assertResult(expected);
    }

    @Test
    public void testJust() {
        String expected = "Hello!";
        TestSingle<String> testSingle = TestSingle.just(expected);
        testSingle.test().assertResult(expected);
    }

    @Test
    public void testAssertSubscribed_success() {
        TestSingle<String> testSingle = TestSingle.just("Hello!");
        testSingle.subscribe();
        testSingle.assertSubscribed();
    }

    @Test(expected = AssertionError.class)
    public void testAssertSubscribed_failure() {
        TestSingle<String> testSingle = TestSingle.just("Hello!");
        testSingle.assertSubscribed();
    }

    @Test
    public void testAssertNotSubscribed_success() {
        TestSingle<String> testSingle = TestSingle.just("Hello!");
        testSingle.assertNotSubscribed();
    }

    @Test(expected = AssertionError.class)
    public void testAssertNotSubscribed_failure() {
        TestSingle<String> testSingle = TestSingle.just("Hello!");
        testSingle.subscribe();
        testSingle.assertNotSubscribed();
    }
}
