package com.sfl.nms.queue.amqp.queues;

import org.springframework.amqp.core.Queue;

import java.util.UUID;

/**
 * @author Ruben Dilanyan
 *
 *         May 18, 2014
 */
public class UniquelyNamedConfigurableQueue extends Queue
{

	/**
	 * @param durable
	 * @param exclusive
	 * @param autoDelete
	 */
	public UniquelyNamedConfigurableQueue(final boolean durable, final boolean exclusive, final boolean autoDelete)
	{
		super(UUID.randomUUID().toString(), durable, exclusive, autoDelete);
	}

}
