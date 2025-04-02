CREATE TABLE IF NOT EXISTS "order" (
    id                  UUID                NOT NULL,
    total               NUMERIC(10, 2)      NOT NULL,
    status              SMALLINT            DEFAULT 0 NOT NULL,
    order_date          TIMESTAMP           NOT NULL,
    processing_date     TIMESTAMP           DEFAULT NOW() NOT NULL,

    CONSTRAINT "pkey_order" PRIMARY KEY (id)
);

CREATE INDEX IF NOT EXISTS idx_order_status ON "order" (status);