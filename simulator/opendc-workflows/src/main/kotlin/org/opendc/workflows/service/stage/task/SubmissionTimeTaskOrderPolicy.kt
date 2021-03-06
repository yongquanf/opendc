/*
 * MIT License
 *
 * Copyright (c) 2020 atlarge-research
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.opendc.workflows.service.stage.task

import org.opendc.workflows.service.StageWorkflowService
import org.opendc.workflows.service.TaskState

/**
 * A [TaskOrderPolicy] that orders tasks based on the order of arrival in the queue.
 */
public data class SubmissionTimeTaskOrderPolicy(public val ascending: Boolean = true) : TaskOrderPolicy {
    override fun invoke(scheduler: StageWorkflowService): Comparator<TaskState> = compareBy<TaskState> {
        it.job.submittedAt.let { if (ascending) it else -it }
    }

    override fun toString(): String {
        return "Submission-Time(${if (ascending) "asc" else "desc"})"
    }
}
