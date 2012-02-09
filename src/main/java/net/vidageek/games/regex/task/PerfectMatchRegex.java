package net.vidageek.games.regex.task;

import net.vidageek.games.task.JudgedTask;
import net.vidageek.games.task.Task;

final public class PerfectMatchRegex implements Task {

	private final String matchingTarget;

	public PerfectMatchRegex(final String matchingTarget) {
		this.matchingTarget = matchingTarget;
	}

	public JudgedTask judge(final String challenge) {
		return new Regex(challenge).match(matchingTarget);
	}

	public String getChallenge() {
		return "Qual regex d&aacute; match em [" + matchingTarget + "]?";
	}

	@Override
	public String toString() {
		return "Match em " + matchingTarget;
	}
}
