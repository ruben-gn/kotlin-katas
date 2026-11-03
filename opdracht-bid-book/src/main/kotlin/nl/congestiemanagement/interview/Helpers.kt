package nl.congestiemanagement.interview

import java.time.Duration
import java.time.Instant

/** Lengte van één kwartier. */
val KWARTIER: Duration = Duration.ofMinutes(15)

/** Begintijden van alle kwartieren vanaf [van] tot (niet tot en met) [tot]. */
fun kwartierenTussen(van: Instant, tot: Instant): List<Instant> =
    generateSequence(van) { it + KWARTIER }.takeWhile { it < tot }.toList()
